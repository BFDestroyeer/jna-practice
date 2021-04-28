package Event;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AbstractEventSerializer implements JsonSerializer<AbstractEvent> {
    @Override
    public JsonElement serialize(AbstractEvent event, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("eventType", String.valueOf(event.type));
        return result;
    }
}
