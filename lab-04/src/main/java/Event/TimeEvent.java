package Event;

public class TimeEvent extends AbstractEvent {
    protected int hours = 0;
    protected int minutes = 0;
    protected int seconds = 0;

    public TimeEvent(EventType type, int hours, int minutes) {
        super(type);
        this.hours = hours;
        this.minutes = minutes;
    }

    public TimeEvent(EventType type, int hours, int minutes, int seconds) {
        super(type);
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return this.hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }
}
