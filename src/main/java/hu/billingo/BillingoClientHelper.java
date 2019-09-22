/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import hu.billingo.dto.InvoicePdfResponse;
import hu.billingo.dto.base.Response;
import hu.billingo.utils.JsonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Billingo client helper.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
final class BillingoClientHelper {

    /**
     * The logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingoClientHelper.class);

    /**
     * Generate fresh JWT token.
     *
     * @param publicKey the public key
     * @param privateKey the private key
     *
     * @return the JWT token
     *
     * @throws NoSuchAlgorithmException no such algorithm
     */
    public static String generateJwtToken(final String publicKey, final String privateKey) throws NoSuchAlgorithmException {
        final String secret = new String(Base64.getEncoder().encode(privateKey.getBytes(Charset.defaultCharset())));
        final Long time = System.currentTimeMillis() / 1000;

        final Map<String, Object> map = new HashMap<>();
        map.put("sub", publicKey);
        map.put("iat", "" + time);
        map.put("exp", "" + (time + 30L));

        final String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    /**
     * REST helper method, call the specified URI with the specified method,
     * send body of 'payload' is not null and process the response.
     *
     * @param <T> the run-time type of the response
     * @param publicKey the public key
     * @param privateKey the private key
     * @param type the run-time type of the response
     * @param method the HTTP method
     * @param uri the REST URI
     * @param queryParams query parameters map
     * @param payload the optional payload
     * @param params the optional list of parameters
     *
     * @return the response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static <T extends Response> T restHelper(
            final String publicKey, final String privateKey,
            final Class<T> type,
            final String method, final String uri,
            final Map<String, String> queryParams,
            final String payload, final Object... params) throws IOException, NoSuchAlgorithmException {
        final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://www.billingo.hu/api/");
        urlBuilder.append(uri);

        for (final Object param : params) {
            urlBuilder.append("/").append(param);
        }

        if (queryParams != null) {
            urlBuilder.append("?");
            queryParams.entrySet().stream().forEach((e) -> {
                urlBuilder.append(e.getKey()).append("=").append(e.getValue()).append("&");
            });
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        LOGGER.info("{} -              Url: {}", method, urlBuilder.toString());

        final String jwtToken = generateJwtToken(publicKey, privateKey);
        LOGGER.info("{} -        JWT token: {}", method, jwtToken);

        final URL url = new URL(urlBuilder.toString());
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + jwtToken);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        if (payload != null) {
            con.setDoOutput(true);
            try (final OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes(Charset.defaultCharset());
                os.write(input, 0, input.length);
                LOGGER.info("{} -   Output content: {}", method, payload);
            }
        }

        final int status = con.getResponseCode();
        LOGGER.info("{} -  Response status: {}", method, status);
        final StringBuilder content = new StringBuilder();
        if (status == 200) {
            if (type.equals(InvoicePdfResponse.class)) {
                int n;
                final byte[] buffer = new byte[4096];

                final InvoicePdfResponse pdfResponse = new InvoicePdfResponse();
                try (final InputStream is = con.getInputStream()) {
                    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        while ((n = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, n);
                        }

                        pdfResponse.setSuccess(Boolean.TRUE);
                        pdfResponse.setContent(baos.toByteArray());
                    }
                }

                if (pdfResponse.getContent() == null) {
                    LOGGER.info("{} - Original content: (no content)", method);
                } else {
                    LOGGER.info("{} - Original content: {} bytes of binary data", method, pdfResponse.getContent().length);
                }

                con.disconnect();
                return (T) pdfResponse;
            } else {
                try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }
            }
        } else {
            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
        }

        con.disconnect();

        LOGGER.info("{} - Original content: {}", method, content.toString());
        final T response = JsonUtils.fromJson(type, content.toString());
        LOGGER.info("{} -   Object content: {}", method, response.toJson());

        return response;
    }
}
