/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO class of an invoice item.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class InvoiceItem {

    @SerializedName("product_uid")
    private Long productUid;
    @SerializedName("description")
    private String description;
    @SerializedName("net_unit_price")
    private Double netUnitPrice;
    @SerializedName("qty")
    private Double qty;
    @SerializedName("unit")
    private String unit;
    @SerializedName("vat_id")
    private Long vatId;
    @SerializedName("item_comment")
    private String itemComment;

    public Long getProductUid() {
        return productUid;
    }

    public void setProductUid(Long productUid) {
        this.productUid = productUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNetUnitPrice() {
        return netUnitPrice;
    }

    public void setNetUnitPrice(Double netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getVatId() {
        return vatId;
    }

    public void setVatId(Long vatId) {
        this.vatId = vatId;
    }

    public String getItemComment() {
        return itemComment;
    }

    public void setItemComment(String itemComment) {
        this.itemComment = itemComment;
    }
}
