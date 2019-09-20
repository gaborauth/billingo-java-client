/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

import hu.billingo.dto.BankAccountsResponse;
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

        final BankAccountsResponse bankAccounts = client.getBankAccounts();

        if (bankAccounts.getData() == null || bankAccounts.getData().isEmpty()) {
            return;
        }

        final Long bankId = bankAccounts.getData().get(0).getId();
        client.getBankAccount(bankId);
    }
}
