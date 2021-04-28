package Event;

import java.util.LinkedList;

public class EventManager {
    LinkedList<IListener> listeners = new LinkedList<>();

    public EventManager() {
    }

    public void subscribe(IListener listener) {
        this.listeners.add(listener);
    }

    public void unsubscribe(IListener listener) {
        this.listeners.remove(listener);
    }

    public void broadcast(AbstractEvent event) {
            new Thread(() -> {
                for (IListener listener: listeners) {
                    listener.signal(event);
                }
            }).start();
    }
}
