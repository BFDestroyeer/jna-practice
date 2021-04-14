package Events;

public interface IListener {
    void signal(AbstractEvent event);
    void connect(ICallable slot);
}
