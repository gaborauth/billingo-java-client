/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;

/**
 * DTO class of a bank account.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class BankAccount {

    @SerializedName("bank_name")
    private String bankName;
    @SerializedName("account_no")
    private String accountNumber;
    @SerializedName("account_no_iban")
    private String accountIban;
    @SerializedName("account_swift")
    private String accountSwift;
    @SerializedName("currency")
    private String currency;
    @SerializedName("uid")
    private Long uid;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }

    public String getAccountSwift() {
        return accountSwift;
    }

    public void setAccountSwift(String accountSwift) {
        this.accountSwift = accountSwift;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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
