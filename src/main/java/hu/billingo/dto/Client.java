/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;

/**
 * DTO class of a billing address.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class Client {

    @SerializedName("name")
    private String name;
    @SerializedName("force")
    private Boolean force;
    @SerializedName("email")
    private String email;
    @SerializedName("taxcode")
    private String taxcode;
    @SerializedName("type")
    private String type;
    @SerializedName("fokonyv_szam")
    private String ledgerAccountNumber;
    @SerializedName("phone")
    private String phone;
    @SerializedName("defaults")
    private ClientDefaults defaults;
    @SerializedName("billing_address")
    private BillingAddress billingAddress;
    @SerializedName("bank")
    private Bank bank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLedgerAccountNumber() {
        return ledgerAccountNumber;
    }

    public void setLedgerAccountNumber(String ledgerAccountNumber) {
        this.ledgerAccountNumber = ledgerAccountNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ClientDefaults getDefaults() {
        return defaults;
    }

    public void setDefaults(ClientDefaults defaults) {
        this.defaults = defaults;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Creates a JSON string.
     *
     * @return the JSON string
     */
    public final String toJson() {
        return JsonUtils.toJson(this);
    }
}
