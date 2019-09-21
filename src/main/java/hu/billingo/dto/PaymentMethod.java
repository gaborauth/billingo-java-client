/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO class of a payment method.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public class PaymentMethod {

    @SerializedName("id")
    private Long id;
    @SerializedName("lang_code")
    private String langCode;
    @SerializedName("name")
    private String name;
    @SerializedName("advance_paid")
    private String advancePaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvancePaid() {
        return advancePaid;
    }

    public void setAdvancePaid(String advancePaid) {
        this.advancePaid = advancePaid;
    }
}
