/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.dto.base;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Base class of list responses.
 *
 * @param <T> the run-time type of the response
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public abstract class ResponseDataList<T> extends Response {

    @SerializedName("type")
    private String type;
    @SerializedName("data")
    private List<Attributes<T>> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Attributes<T>> getData() {
        return data;
    }

    public void setData(List<Attributes<T>> data) {
        this.data = data;
    }
}
