package Watch;

import AlarmClock.IAlarmClock;

/**
 * Watches with hour, minute and second hands
 */
public class AdvancedWatch extends Watch.SimpleWatch {
    protected int seconds;

    @Override
    protected void broadcastTime() {
        manager.broadcast(new TimeEvent(this.hours, this.minutes, this.seconds));
    }

    public AdvancedWatch(String name, double price) {
        super(name, price);
        this.seconds = 0;
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        if (seconds < 0 || seconds > 59) {
            throw new Exception("Invalid seconds");
        }
        this.seconds = seconds;
        this.broadcastTime();
    }

    @Override
    public void addSeconds(int seconds) throws Exception{
        addMinutes((this.seconds + seconds) / 60);
        this.seconds = (this.seconds + seconds) % 60;
        this.broadcastTime();
    }

    @Override
    public int getSeconds() {
        return this.seconds;
    }

    @Override
    public String toString() {
        return this.hours + ":" + this.minutes + ":" + this.seconds;
    }

    @Override
    public boolean hasSecondsHand() {
        return true;
    }
}
