/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo;

/**
 * Protected Billingo client builder.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public final class BillingoClientBuilder {

    /**
     * The public key.
     */
    private String publicKey;
    /**
     * The private key.
     */
    private String privateKey;

    /**
     * Protected constructor.
     */
    protected BillingoClientBuilder() {
    }

    /**
     * Add public key.
     *
     * @param publicKey the public key of the Billingo account.
     *
     * @return the builder
     */
    public BillingoClientBuilder publicKey(final String publicKey) {
        this.publicKey = publicKey;

        return this;
    }

    /**
     * Add private key.
     *
     * @param privateKey the private key of the Billingo account.
     *
     * @return the builder
     */
    public BillingoClientBuilder privateKey(final String privateKey) {
        this.privateKey = privateKey;

        return this;
    }

    /**
     * Build a client.
     *
     * @return the client
     *
     * @throws IllegalStateException configuration exception
     */
    public BillingoClient build() throws IllegalStateException {
        if (publicKey == null) {
            throw new IllegalStateException("The 'publicKey' is not configured...");
        }
        if (privateKey == null) {
            throw new IllegalStateException("The 'privateKey' is not configured...");
        }

        return new BillingoClient(this.publicKey, this.privateKey);
    }
}
