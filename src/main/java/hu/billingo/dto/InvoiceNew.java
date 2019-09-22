/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;
import java.util.List;

/**
 * DTO class of an invoice to create as new.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class InvoiceNew {

    @SerializedName("fulfillment_date")
    private String fulfillmentDate;
    @SerializedName("due_date")
    private String dueDate;
    @SerializedName("payment_method")
    private Long paymentMethod;
    @SerializedName("comment")
    private String comment;
    @SerializedName("template_lang_code")
    private String templateLangCode;
    @SerializedName("electronic_invoice")
    private Long electronic_invoice;
    @SerializedName("currency")
    private String currency;
    @SerializedName("exchange_rate")
    private Double exchange_rate;
    @SerializedName("client_uid")
    private Long clientUid;
    @SerializedName("block_uid")
    private Long blockUid;
    @SerializedName("type")
    private Long type;
    @SerializedName("round_to")
    private Long roundTo;
    @SerializedName("bank_account_uid")
    private Long bankAccountUid;
    @SerializedName("items")
    private List<InvoiceItem> items;

    public String getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(String fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTemplateLangCode() {
        return templateLangCode;
    }

    public void setTemplateLangCode(String templateLangCode) {
        this.templateLangCode = templateLangCode;
    }

    public Long getElectronic_invoice() {
        return electronic_invoice;
    }

    public void setElectronic_invoice(Long electronic_invoice) {
        this.electronic_invoice = electronic_invoice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(Double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public Long getClientUid() {
        return clientUid;
    }

    public void setClientUid(Long clientUid) {
        this.clientUid = clientUid;
    }

    public Long getBlockUid() {
        return blockUid;
    }

    public void setBlockUid(Long blockUid) {
        this.blockUid = blockUid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getRoundTo() {
        return roundTo;
    }

    public void setRoundTo(Long roundTo) {
        this.roundTo = roundTo;
    }

    public Long getBankAccountUid() {
        return bankAccountUid;
    }

    public void setBankAccountUid(Long bankAccountUid) {
        this.bankAccountUid = bankAccountUid;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
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
