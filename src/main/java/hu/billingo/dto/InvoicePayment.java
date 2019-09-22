/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;

/**
 * DTO class of an invoice payment.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class InvoicePayment {

    @SerializedName("date")
    private String date;
    @SerializedName("amount")
    private Double amount;
    @SerializedName("payment_method")
    private Long paymentMethod;
    @SerializedName("fully_paid")
    private Boolean fullyPaid;
    @SerializedName("outstanding")
    private Double outstanding;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getFullyPaid() {
        return fullyPaid;
    }

    public void setFullyPaid(Boolean fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
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
