/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto.base;

import com.google.gson.annotations.SerializedName;

/**
 * Base class of single responses.
 *
 * @param <T> the run-time type of the response
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public abstract class ResponseData<T> extends Response {

    @SerializedName("type")
    private String type;
    @SerializedName("data")
    private T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
