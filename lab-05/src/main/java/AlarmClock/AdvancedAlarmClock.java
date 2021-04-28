package AlarmClock;

import Event.AbstractEvent;
import Event.EventType;
import Event.TimeEvent;

public class AdvancedAlarmClock extends SimpleAlarmClock {
    private int seconds;

    public AdvancedAlarmClock() {
        seconds = 0;
    }

    @Override
    public void signal(AbstractEvent event) {
        if (event.type == EventType.TIME_UPDATE) {
            TimeEvent timeEvent = (TimeEvent) event;
            if (timeEvent.getHours() == this.hours && timeEvent.getMinutes() == this.minutes &&
                    timeEvent.getSeconds() == this.seconds) {
                eventManager.broadcast(new AbstractEvent(EventType.ALARM));
            }
        }
    }

    @Override
    public void setAlarmSeconds(int seconds) throws Exception {
        if (seconds < 0 || seconds > 59) {
            throw new Exception("Invalid seconds");
        }
        this.seconds = seconds;
    }

    @Override
    public int getAlarmSeconds() {
        return this.seconds;
    }
}
