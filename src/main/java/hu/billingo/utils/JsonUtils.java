/**
 * CC-LGPL 2.1 (http://creativecommons.org/licenses/LGPL/2.1/)
 */
package hu.billingo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import hu.billingo.dto.ClientDefaults;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * JSON utility methods.
 *
 * @author <a href="mailto:gabor.auth@iotguru.cloud">Gábor AUTH</a>
 */
public final class JsonUtils {

    /**
     * Custom Date deserializer.
     */
    private static final JsonDeserializer<Date> DATE_DESERIALIZER = (j, t, c) -> new Date(j.getAsLong());

    /**
     * Custom Date serializer.
     */
    private static final JsonSerializer<Date> DATE_SERIALIZER = (s, t, c) -> new JsonPrimitive(s.getTime());

    /**
     * Custom client defaults deserializer.
     */
    private static final JsonDeserializer<ClientDefaults> CLIENT_DEFAULTS_DESERIALIZER = (j, t, c) -> {
        try {
            return new Gson().fromJson(j, t);
        } catch (JsonSyntaxException e) {
            return null;
        }
    };

    /**
     * Create T instance from JSON string.
     *
     * @param <T> the T class
     * @param type the type of T class
     * @param json the JSON string
     * @return the instance
     */
    public static <T> T fromJson(final Class<T> type, final String json) {
        return objectFromJson(type, json);
    }

    /**
     * Create list of T instance from JSON string.
     *
     * @param <T> the T class
     * @param type the type of T class
     * @param json the JSON string
     * @return the instance
     */
    public static <T> List<T> listFromJson(final Class<T> type, final String json) {
        final T[] array = (T[]) Array.newInstance(type, 0);
        return Arrays.asList((T[]) objectFromJson(array.getClass(), json));
    }

    /**
     * Create T instance from JSON string.
     *
     * @param <T> the T class
     * @param type the type of T class
     * @param json the JSON string
     * @return the instance
     */
    public static <T> T objectFromJson(final Class<T> type, final String json) {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, DATE_DESERIALIZER);
        builder.registerTypeAdapter(Date.class, DATE_SERIALIZER);
        builder.registerTypeAdapter(ClientDefaults.class, CLIENT_DEFAULTS_DESERIALIZER);
        return builder.create().fromJson(json, type);
    }

    /**
     * Create JSON string from the Object instance.
     *
     * @param object the object
     * @return the JSON string
     */
    public static String toJson(final Object object) {
        return objectToJson(object);
    }

    /**
     * Create JSON string from the Object instance.
     *
     * @param object the object
     * @return the JSON string
     */
    public static String objectToJson(final Object object) {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, DATE_DESERIALIZER);
        builder.registerTypeAdapter(Date.class, DATE_SERIALIZER);
        builder.registerTypeAdapter(ClientDefaults.class, CLIENT_DEFAULTS_DESERIALIZER);
        return builder.create().toJson(object);
    }
}
