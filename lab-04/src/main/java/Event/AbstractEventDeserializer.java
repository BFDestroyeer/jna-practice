package Event;

import java.lang.reflect.Type;

import com.google.gson.*;

class AbstractEventDeserializer implements JsonDeserializer<AbstractEvent> {
    @Override
    public AbstractEvent deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        EventType eventType = EventType.valueOf(object.get("eventType").getAsString());
        return new AbstractEvent(eventType);
    }
}
