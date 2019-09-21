/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO class of a bank account of client.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class Bank {

    @SerializedName("account_no")
    private String accountNumber;
    @SerializedName("iban")
    private String accountIban;
    @SerializedName("swift")
    private String accountSwift;

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
}
