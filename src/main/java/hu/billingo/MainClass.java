/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import hu.billingo.dto.Bank;
import hu.billingo.dto.BillingAddress;
import hu.billingo.dto.Client;
import hu.billingo.dto.ClientDefaults;
import hu.billingo.dto.ClientListResponse;
import hu.billingo.dto.ClientResponse;
import hu.billingo.dto.Expense;
import hu.billingo.dto.ExpenseListResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Speedy test of the client library.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class MainClass {

    /**
     * Entry method of main class.
     *
     * @param args list of parameters
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static final void main(final String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length < 2) {
            System.out.println("Usage: java -jar billingo-java-client.jar <publicKey> <privateKey>");
            return;
        }

        final String publicKey = args[0];
        final String privateKey = args[1];

        final BillingoClient billingoClient = BillingoClient.builder()
                .publicKey(publicKey)
                .privateKey(privateKey)
                .build();

        billingoClient.getBankAccounts();

        System.out.println("====");

        final ClientListResponse clientList = billingoClient.getClients(null, null);

        System.out.println("====");

        if (clientList.getData() != null && clientList.getData().size() > 0) {
            billingoClient.getClient(clientList.getData().get(0).getId());
        }

        System.out.println("====");

        final Client client = new Client();
        client.setName("Gigazoom LLC.");
        client.setEmail("rbrooks5@amazon.com");
        client.setTaxcode("123456");
        client.setForce(Boolean.FALSE);

        final BillingAddress ba = new BillingAddress();
        ba.setStreetName("Moulton");
        ba.setStreetType("Terrace");
        ba.setHouseNr("2797");
        ba.setBlock("A");
        ba.setEntrance("B");
        ba.setFloor("3.");
        ba.setDoor("17");
        ba.setCity("Preston");
        ba.setPostcode("PR1");
        ba.setDistrict("XII");
        ba.setCountry("United Kingdom");
        client.setBillingAddress(ba);

        client.setPhone("");

        final Bank bank = new Bank();
        bank.setAccountNumber("12345678-12345678-12345678");
        bank.setAccountIban("");
        bank.setAccountSwift("");
        client.setBank(bank);

        client.setLedgerAccountNumber("");
        client.setType("2");

        final ClientDefaults defaults = new ClientDefaults();

        defaults.setPaymentMethod("1");
        defaults.setElectronicInvoice("0");
        defaults.setInvoiceDueDays("3");
        defaults.setInvoiceCurrency("HUF");
        defaults.setInvoiceTemplateLangCode("hu");
        client.setDefaults(defaults);

        final ClientResponse cr = billingoClient.createClient(client);

        System.out.println("====");

        client.setName("Gigazoom Ltd");
        billingoClient.updateClient(client, cr.getData().getId());

        System.out.println("====");

        billingoClient.deleteClient(cr.getData().getId());

        System.out.println("====");

        billingoClient.getCurrency("EUR", "HUF", 1.0);

        System.out.println("====");

        billingoClient.getExpenseCategories("hu");

        System.out.println("====");

        final ExpenseListResponse er = billingoClient.getExpenses(null, null);

        System.out.println("====");

        if (er.getData() == null || er.getData().isEmpty()) {
            final Expense expense = new Expense();
            expense.setCategoriesId(2L);
            expense.setName("Office Supplies");
            expense.setInvoiceNo("2019-000002");
            expense.setClientUid(cr.getData().getId());
            expense.setGrossPrice(1000.0);
            expense.setVat(1L);
            expense.setCurrency("HUF");
            expense.setDueDate("2017-01-15");

            billingoClient.createExpense(expense);
        } else {
            final Long expenseId = er.getData().get(0).getId();
            final Expense expense = er.getData().get(0).getAttributes();
            
            expense.setName("Office Supplies Updated");
            billingoClient.updateExpense(expense, expenseId);
        }

        System.out.println("====");

        billingoClient.getPaymentMethod("hu");

        System.out.println("====");

        billingoClient.getVats();

        System.out.println("====");

        billingoClient.getVatEu("DE", "104.20.46.161", "DE", null);

        System.out.println("====");
    }
}
