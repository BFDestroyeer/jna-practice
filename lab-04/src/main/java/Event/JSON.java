package Event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSON {
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(AbstractEvent.class, new AbstractEventSerializer())
            .registerTypeAdapter(AbstractEvent.class, new AbstractEventDeserializer())
            .create();

    public static Gson get() {
        return gson;
    }
}
