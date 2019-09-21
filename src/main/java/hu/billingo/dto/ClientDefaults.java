/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO class of a client default settings.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class ClientDefaults {

    @SerializedName("payment_method")
    private String paymentMethod;
    @SerializedName("electronic_invoice")
    private String electronicInvoice;
    @SerializedName("invoice_due_days")
    private String invoiceDueDays;
    @SerializedName("invoice_currency")
    private String invoiceCurrency;
    @SerializedName("invoice_template_lang_code")
    private String invoiceTemplateLangCode;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getElectronicInvoice() {
        return electronicInvoice;
    }

    public void setElectronicInvoice(String electronicInvoice) {
        this.electronicInvoice = electronicInvoice;
    }

    public String getInvoiceDueDays() {
        return invoiceDueDays;
    }

    public void setInvoiceDueDays(String invoiceDueDays) {
        this.invoiceDueDays = invoiceDueDays;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public String getInvoiceTemplateLangCode() {
        return invoiceTemplateLangCode;
    }

    public void setInvoiceTemplateLangCode(String invoiceTemplateLangCode) {
        this.invoiceTemplateLangCode = invoiceTemplateLangCode;
    }
}
