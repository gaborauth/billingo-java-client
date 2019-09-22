/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto.base;

import com.google.gson.annotations.SerializedName;
import hu.billingo.utils.JsonUtils;
import java.util.List;

/**
 * Base class of responses.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public abstract class Response {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("id")
    private Long id;
    @SerializedName("error")
    private String error;
    @SerializedName("errors")
    private List<String> errors;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
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
