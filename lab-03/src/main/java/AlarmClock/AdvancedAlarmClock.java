package AlarmClock;

import Events.AbstractEvent;
import Watch.TimeEvent;

public class AdvancedAlarmClock extends SimpleAlarmClock {
    private int seconds;

    public AdvancedAlarmClock() {
        seconds = 0;
    }

    @Override
    public void signal(AbstractEvent event) {
        TimeEvent timeEvent = (TimeEvent) event;
        if (timeEvent.getHours() == this.hours && timeEvent.getMinutes() == minutes &&
                timeEvent.getSeconds() == this.seconds) {
            slot.call();
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
