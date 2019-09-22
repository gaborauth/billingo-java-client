/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * DTO class of an invoice.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class Invoice {

    @SerializedName("date")
    private String date;
    @SerializedName("fulfillment_date")
    private String fulfillmentDate;
    @SerializedName("due_date")
    private String dueDate;
    @SerializedName("invoice_no")
    private String invoiceNo;
    @SerializedName("total")
    private Double total;
    @SerializedName("total_paid")
    private Double totalPaid;
    @SerializedName("comment")
    private String comment;
    @SerializedName("currency")
    private String currency;
    @SerializedName("client_uid")
    private Long clientUid;
    @SerializedName("block_uid")
    private Long blockUid;
    @SerializedName("uid")
    private Long uid;
    @SerializedName("items")
    private List<InvoiceItem> items;
    @SerializedName("client")
    private Client client;
    @SerializedName("payment_method")
    private PaymentMethod paymentMethod;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
