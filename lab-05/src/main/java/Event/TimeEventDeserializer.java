package Event;

import java.lang.reflect.Type;

import com.google.gson.*;

class TimeEventDeserializer implements JsonDeserializer<TimeEvent> {
    @Override
    public TimeEvent deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        EventType eventType = EventType.valueOf(object.get("eventType").getAsString());
        int hours = object.get("hours").getAsInt();
        int minutes = object.get("minutes").getAsInt();
        int seconds = object.get("seconds").getAsInt();
        return new TimeEvent(eventType, hours, minutes, seconds);
    }
}