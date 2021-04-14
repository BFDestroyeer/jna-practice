package Events;

public interface IPublisher {
    void addListener(IListener listener);
    void removeListener(IListener listener);
}
