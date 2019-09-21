/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import static hu.billingo.BillingoClientHelper.restHelper;
import hu.billingo.dto.BankAccount;
import hu.billingo.dto.BankAccountListResponse;
import hu.billingo.dto.BankAccountResponse;
import hu.billingo.dto.VatEuResponse;
import hu.billingo.dto.VatListResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Billingo client.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public final class BillingoClient {

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
    public BankAccountListResponse getBankAccount(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, BankAccountListResponse.class, "GET", "bank_accounts", null, null, id);
    }

    /**
     * Return the list of bank accounts.
     *
     * @return the list of bank accounts
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public BankAccountListResponse getBankAccounts() throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, BankAccountListResponse.class, "GET", "bank_accounts", null, null);
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
        return restHelper(publicKey, privateKey, BankAccountResponse.class, "POST", "bank_accounts", null, account.toJson());
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
        return restHelper(publicKey, privateKey, BankAccountResponse.class, "PUT", "bank_accounts", null, account.toJson(), id);
    }

    /**
     * Return the EU VAT.
     *
     * @param country the county code given by the user
     * @param ip the IP address of the user
     * @param businessCountry the country code for the operating business
     * @param vatCode the EU VAT code
     *
     * @return the EU VAT
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public VatEuResponse getVatEu(final String country, final String ip, final String businessCountry, final String vatCode) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("country", country);
        queryParams.put("ip", ip);
        queryParams.put("business_country", businessCountry);
        if (vatCode != null) {
            queryParams.put("vat_code", vatCode);
        }

        return restHelper(publicKey, privateKey, VatEuResponse.class, "GET", "vat/eu", queryParams, null);
    }

    /**
     * Return the list of VATs.
     *
     * @return the list of VATs
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public VatListResponse getVats() throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, VatListResponse.class, "GET", "vat", null, null);
    }

    /**
     * Return the list of VATs with value filter.
     *
     * @param valueFilter the value filter
     *
     * @return the list of VATs
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public VatListResponse getVats(final Double valueFilter) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("v", "" + valueFilter);

        return restHelper(publicKey, privateKey, VatListResponse.class, "GET", "vat", queryParams, null);
    }

    /**
     * Return the list of VATs with description filter.
     *
     * @param descriptionFilter the description filter
     *
     * @return the list of VATs
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public VatListResponse getVats(final String descriptionFilter) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("d", descriptionFilter);

        return restHelper(publicKey, privateKey, VatListResponse.class, "GET", "vat", queryParams, null);
    }

    /**
     * Return the list of VATs with value and description filter.
     *
     * @param valueFilter the value filter
     * @param descriptionFilter the description filter
     *
     * @return the list of VATs
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public VatListResponse getVats(final Double valueFilter, final String descriptionFilter) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("v", "" + valueFilter);
        queryParams.put("d", descriptionFilter);

        return restHelper(publicKey, privateKey, VatListResponse.class, "GET", "vat", queryParams, null);
    }
}
