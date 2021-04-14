package Event;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TimeEventSerializer implements JsonSerializer<TimeEvent> {
    @Override
    public JsonElement serialize(TimeEvent event, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.addProperty("eventType", String.valueOf(event.type));

        result.addProperty("hours", event.getHours());
        result.addProperty("minutes", event.getMinutes());
        result.addProperty("seconds", event.getSeconds());

        return result;
    }
}
