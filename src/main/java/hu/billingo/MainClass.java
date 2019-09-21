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

        final BillingoClient client = BillingoClient.builder()
                .publicKey(publicKey)
                .privateKey(privateKey)
                .build();

        client.getBankAccounts();

        System.out.println("====");

        final ClientListResponse clientList = client.getClients(null, null);

        System.out.println("====");

        if (clientList.getData() != null && clientList.getData().size() > 0) {
            client.getClient(clientList.getData().get(0).getId());
        }

        System.out.println("====");

        final Client c = new Client();
        c.setName("Gigazoom LLC.");
        c.setEmail("rbrooks5@amazon.com");
        c.setTaxcode("123456");
        c.setForce(Boolean.FALSE);

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
        c.setBillingAddress(ba);

        c.setPhone("");

        final Bank bank = new Bank();
        bank.setAccountNumber("12345678-12345678-12345678");
        bank.setAccountIban("");
        bank.setAccountSwift("");
        c.setBank(bank);

        c.setLedgerAccountNumber("");
        c.setType("2");

        final ClientDefaults defaults = new ClientDefaults();

        defaults.setPaymentMethod("1");
        defaults.setElectronicInvoice("0");
        defaults.setInvoiceDueDays("3");
        defaults.setInvoiceCurrency("HUF");
        defaults.setInvoiceTemplateLangCode("hu");
        c.setDefaults(defaults);

        final ClientResponse cr = client.createClient(c);

        System.out.println("====");

        c.setName("Gigazoom Ltd");
        client.updateClient(c, cr.getData().getId());

        System.out.println("====");

        client.deleteClient(cr.getData().getId());

        System.out.println("====");

        client.getCurrency("EUR", "HUF", 1.0);

        System.out.println("====");

        client.getExpenseCategories("hu");

        System.out.println("====");
        
        client.getPaymentMethod("hu");

        System.out.println("====");

        client.getVats();

        System.out.println("====");

        client.getVatEu("DE", "104.20.46.161", "DE", null);

        System.out.println("====");
    }
}
