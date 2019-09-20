/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import hu.billingo.dto.BankAccount;
import hu.billingo.dto.BankAccountResponse;
import hu.billingo.dto.BankAccountsResponse;
import hu.billingo.dto.base.Response;
import hu.billingo.utils.JsonUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.BufferedReader;
import java.io.IOException;
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
 * Billingo client.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public final class BillingoClient {

    /**
     * The logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingoClient.class);

    /**
     * The public key.
     */
    private final String publicKey;
    /**
     * The private key.
     */
    private final String privateKey;

    /**
     * Protected constructor.
     *
     * @param publicKey the public key
     * @param privateKey the private key
     */
    protected BillingoClient(final String publicKey, final String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Create a new client builder.
     *
     * @return client builder.
     */
    public static final BillingoClientBuilder builder() {
        return new BillingoClientBuilder();
    }

    /**
     * Return the list of bank accounts.
     *
     * @param id the id of the bank account
     *
     * @return the list of bank accounts
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public BankAccountsResponse getBankAccount(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(BankAccountsResponse.class, "GET", "bank_accounts", null, id);
    }

    /**
     * Return the list of bank accounts.
     *
     * @return the list of bank accounts
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public BankAccountsResponse getBankAccounts() throws IOException, NoSuchAlgorithmException {
        return restHelper(BankAccountsResponse.class, "GET", "bank_accounts", null);
    }

    /**
     * Create a new bank account.
     *
     * @param account the bank account
     *
     * @return the bank account response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public BankAccountResponse createBankAccount(final BankAccount account) throws IOException, NoSuchAlgorithmException {
        return restHelper(BankAccountResponse.class, "POST", "bank_accounts", account.toJson());
    }

    /**
     * Update the bank account.
     *
     * @param account the bank account
     * @param id the id of the bank account
     *
     * @return the bank account response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public BankAccountResponse updateBankAccount(final BankAccount account, final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(BankAccountResponse.class, "PUT", "bank_accounts", account.toJson(), id);
    }

    /**
     * Generate fresh JWT token.
     *
     * @return the JWT token
     *
     * @throws NoSuchAlgorithmException no such algorithm
     */
    private String generateJwtToken() throws NoSuchAlgorithmException {
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
     * @param type the run-time type of the response
     * @param method the HTTP method
     * @param uri the REST URI
     * @param payload the optional payload
     * @param params the optional list of parameters
     * @return the response
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private <T extends Response> T restHelper(final Class<T> type, final String method, final String uri, final String payload, final Object... params) throws IOException, NoSuchAlgorithmException {
        final StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://www.billingo.hu/api/");
        urlBuilder.append(uri);

        for (final Object param : params) {
            urlBuilder.append("/").append(param);
        }

        final String jwtToken = generateJwtToken();

        LOGGER.info("{} -              Url: {}", method, urlBuilder.toString());
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
            try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
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
