/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO class of a EU VAT.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class VatEu {

    @SerializedName("set_country")
    private String setCountry;
    @SerializedName("ip_country")
    private String ipCountry;
    @SerializedName("country_match")
    private Boolean countryMatch;
    @SerializedName("company")
    private String company;
    @SerializedName("tax_rate")
    private Double taxRate;
    @SerializedName("billingo_vat")
    private Vat billingoVat;

    public String getSetCountry() {
        return setCountry;
    }

    public void setSetCountry(String setCountry) {
        this.setCountry = setCountry;
    }

    public String getIpCountry() {
        return ipCountry;
    }

    public void setIpCountry(String ipCountry) {
        this.ipCountry = ipCountry;
    }

    public Boolean getCountryMatch() {
        return countryMatch;
    }

    public void setCountryMatch(Boolean countryMatch) {
        this.countryMatch = countryMatch;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Vat getBillingoVat() {
        return billingoVat;
    }

    public void setBillingoVat(Vat billingoVat) {
        this.billingoVat = billingoVat;
    }
}
