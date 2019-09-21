/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;

/**
 * DTO class of a expense.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class Expense {

    @SerializedName("categories_id")
    private Long categoriesId;
    @SerializedName("name")
    private String name;
    @SerializedName("invoice_no")
    private String invoiceNo;
    @SerializedName("gross_price")
    private Double grossPrice;
    @SerializedName("vat")
    private Long vat;
    @SerializedName("client")
    private Client client;
    @SerializedName("client_uid")
    private Long clientUid;
    @SerializedName("due_date")
    private String dueDate;
    @SerializedName("currency")
    private String currency;

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public Long getVat() {
        return vat;
    }

    public void setVat(Long vat) {
        this.vat = vat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getClientUid() {
        return clientUid;
    }

    public void setClientUid(Long clientUid) {
        this.clientUid = clientUid;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
