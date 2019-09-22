/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import static hu.billingo.BillingoClientHelper.restHelper;
import hu.billingo.dto.BankAccount;
import hu.billingo.dto.BankAccountListResponse;
import hu.billingo.dto.BankAccountResponse;
import hu.billingo.dto.Client;
import hu.billingo.dto.ClientListResponse;
import hu.billingo.dto.ClientResponse;
import hu.billingo.dto.CurrencyResponse;
import hu.billingo.dto.Expense;
import hu.billingo.dto.ExpenseCategoryListResponse;
import hu.billingo.dto.ExpenseListResponse;
import hu.billingo.dto.ExpenseResponse;
import hu.billingo.dto.InvoiceBlockListResponse;
import hu.billingo.dto.InvoiceCodeResponse;
import hu.billingo.dto.InvoiceFromProformaResponse;
import hu.billingo.dto.InvoiceListResponse;
import hu.billingo.dto.InvoiceNew;
import hu.billingo.dto.InvoicePayment;
import hu.billingo.dto.InvoicePaymentResponse;
import hu.billingo.dto.InvoicePdfResponse;
import hu.billingo.dto.InvoiceResponse;
import hu.billingo.dto.InvoiceSendResponse;
import hu.billingo.dto.PaymentMethodListResponse;
import hu.billingo.dto.VatEuResponse;
import hu.billingo.dto.VatListResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Billingo client.
 *
 * @TODO: Missing: Recurring, Invoice query, Products
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
     * Return the list of clients.
     *
     * @param page the page
     * @param maxPerPage maximum number of results
     *
     * @return the list of clients
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ClientListResponse getClients(final Integer page, final Integer maxPerPage) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        if (page != null) {
            queryParams.put("page", "" + page);
        }
        if (maxPerPage != null) {
            queryParams.put("max_per_page", "" + maxPerPage);
        }

        return restHelper(publicKey, privateKey, ClientListResponse.class, "GET", "clients", queryParams, null);
    }

    /**
     * Return the clients.
     *
     * @param id the id of the client
     *
     * @return the list of client
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ClientListResponse getClient(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ClientListResponse.class, "GET", "clients", null, null, id);
    }

    /**
     * Create a new client.
     *
     * @param client the client
     *
     * @return the client response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ClientResponse createClient(final Client client) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ClientResponse.class, "POST", "clients", null, client.toJson());
    }

    /**
     * Update the client.
     *
     * @param client the client
     * @param id the id of the client
     *
     * @return the client response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ClientResponse updateClient(final Client client, final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ClientResponse.class, "PUT", "clients", null, client.toJson(), id);
    }

    /**
     * Delete the client.
     *
     * @param id the id of the client
     *
     * @return the client response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ClientResponse deleteClient(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ClientResponse.class, "DELETE", "clients", null, null, id);
    }

    /**
     * Convert currency.
     *
     * @param from the currency to convert from
     * @param to the currency to convert to
     * @param value the amount to convert
     *
     * @return the currency response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public CurrencyResponse getCurrency(final String from, final String to, final Double value) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        queryParams.put("from", from);
        queryParams.put("to", to);
        queryParams.put("value", "" + value);

        return restHelper(publicKey, privateKey, CurrencyResponse.class, "GET", "currency", queryParams, null);
    }

    /**
     * List of expense categories.
     *
     * @param langCode the language code
     *
     * @return the expense categories response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ExpenseCategoryListResponse getExpenseCategories(final String langCode) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ExpenseCategoryListResponse.class, "GET", "expenses/categories", null, null, langCode);
    }

    /**
     * Return the list of expenses.
     *
     * @param page the page
     * @param maxPerPage maximum number of results
     *
     * @return the list of expenses
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ExpenseListResponse getExpenses(final Integer page, final Integer maxPerPage) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        if (page != null) {
            queryParams.put("page", "" + page);
        }
        if (maxPerPage != null) {
            queryParams.put("max_per_page", "" + maxPerPage);
        }

        return restHelper(publicKey, privateKey, ExpenseListResponse.class, "GET", "expenses", queryParams, null);
    }

    /**
     * Create a new expense.
     *
     * @param expense the expense
     *
     * @return the expense response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ExpenseResponse createExpense(final Expense expense) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ExpenseResponse.class, "POST", "expenses", null, expense.toJson());
    }

    /**
     * Update the expense.
     *
     * @param expense the expense
     * @param id the id of the expense
     *
     * @return the expense response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public ExpenseResponse updateExpense(final Expense expense, final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, ExpenseResponse.class, "PUT", "expenses", null, expense.toJson(), id);
    }

    /**
     * Return the list of invoices.
     *
     * @param page the page
     * @param maxPerPage maximum number of results
     *
     * @return the list of invoices
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceListResponse getInvoices(final Integer page, final Integer maxPerPage) throws IOException, NoSuchAlgorithmException {
        final Map<String, String> queryParams = new HashMap<>();
        if (page != null) {
            queryParams.put("page", "" + page);
        }
        if (maxPerPage != null) {
            queryParams.put("max_per_page", "" + maxPerPage);
        }

        return restHelper(publicKey, privateKey, InvoiceListResponse.class, "GET", "invoices", queryParams, null);
    }

    /**
     * Return the invoice.
     *
     * @param id the id of the invoice
     *
     * @return the list of invoice
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceListResponse getInvoice(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceListResponse.class, "GET", "invoices", null, null, id);
    }

    /**
     * Create a new invoice.
     *
     * @param invoice the invoice
     *
     * @return the invoice response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceResponse createInvoice(final InvoiceNew invoice) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceResponse.class, "POST", "invoices", null, invoice.toJson());
    }

    /**
     * Return the download code of an invoice.
     *
     * @param id the id of the invoice
     *
     * @return the download code of invoice
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceCodeResponse getInvoiceDownloadCode(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceCodeResponse.class, "GET", "invoices", null, null, id, "code");
    }

    /**
     * Generate normal invoice from proforma invoice.
     *
     * @param id the id of the proforma invoice
     *
     * @return the invoice id response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceFromProformaResponse getInvoiceFromProforma(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceFromProformaResponse.class, "GET", "invoices", null, null, id, "generate");
    }

    /**
     * Return the PDF content of an invoice.
     *
     * @param id the id of the invoice
     *
     * @return the download code of invoice
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoicePdfResponse getInvoicePdf(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoicePdfResponse.class, "GET", "invoices", null, null, id, "download");
    }

    /**
     * Cancel the invoice.
     *
     * @param id the id of the invoice
     *
     * @return the invoice response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceResponse cancelInvoice(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceResponse.class, "GET", "invoices", null, null, id, "cancel");
    }

    /**
     * Send the invoice.
     *
     * @param id the id of the invoice
     *
     * @return the invoice send response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceSendResponse sendInvoice(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceSendResponse.class, "GET", "invoices", null, null, id, "send");
    }

    /**
     * Update payment of an invoice.
     *
     * @param id the id of the invoice
     * @param payment the payment details
     *
     * @return the payment response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoicePaymentResponse updateInvoicePayment(final Long id, final InvoicePayment payment) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoicePaymentResponse.class, "POST", "invoices", null, payment.toJson(), id, "pay");
    }

    /**
     * Undo payment of an invoice.
     *
     * @param id the id of the invoice
     *
     * @return the payment response
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoicePaymentResponse undoInvoicePayment(final Long id) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoicePaymentResponse.class, "DELETE", "invoices", null, null, id, "pay");
    }

    /**
     * Return the list of invoice blocks.
     *
     * @return the list of invoice blocks
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public InvoiceBlockListResponse getInvoiceBlocks() throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, InvoiceBlockListResponse.class, "GET", "invoices/blocks", null, null);
    }

    /**
     * Return the list of payment methods.
     *
     * @param langCode language code of the payment methods
     *
     * @return the list of payment methods
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public PaymentMethodListResponse getPaymentMethod(final String langCode) throws IOException, NoSuchAlgorithmException {
        return restHelper(publicKey, privateKey, PaymentMethodListResponse.class, "GET", "payment_methods", null, null, langCode);
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
