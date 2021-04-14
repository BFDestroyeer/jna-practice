package AlarmClock;

import Event.*;

public class SimpleAlarmClock implements IAlarmClock, IListener, IPublisher {
    protected int hours;
    protected int minutes;

    protected  EventManager eventManager = new EventManager();

    public SimpleAlarmClock() {
        hours = 0;
        minutes = 0;
    }

    @Override
    public void addListener(IListener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        eventManager.unsubscribe(listener);
    }

    @Override
    public void signal(AbstractEvent event) {
        if (event.type == EventType.WATCH_UPDATED) {
            TimeEvent timeEvent = (TimeEvent) event;
            if (timeEvent.getHours() == this.hours && timeEvent.getMinutes() == this.minutes) {
                eventManager.broadcast(new AbstractEvent(EventType.ALARM));
            }
        }
    }

    @Override
    public void setAlarmHours(int hours) throws Exception {
        if (hours < 0 || hours > 11) {
            throw new Exception("Invalid hours");
        }
        this.hours = hours % 12;
    }

    @Override
    public void setAlarmMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Invalid minutes");
        }
        this.minutes = minutes;
    }

    @Override
    public void setAlarmSeconds(int seconds) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public int getAlarmHours() {
        return this.hours;
    }

    @Override
    public int getAlarmMinutes() {
        return this.minutes;
    }

    public int getAlarmSeconds() throws Exception {
        throw new Exception("Not implemented");
    }
}
