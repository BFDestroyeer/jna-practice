package Watch;

/**
 * Watches with hour and minute hands
 */
public class SimpleWatch extends Watch.AbstractWatch {
    protected int hours;
    protected int minutes;

    public SimpleWatch(String name, double price) {
        super(name, price);
        this.hours = 0;
        this.minutes = 0;
    }

    public void setTime(int hours, int minutes) throws Exception {
        if ((hours < 0) || (hours > 23)) {
            throw new Exception("Invalid hours value");
        }
        if ((minutes < 0) || (minutes > 59)) {
            throw new Exception("Invalid minutes value");
        }
        this.hours = hours % 12;
        this.minutes = minutes;
    }

    public void addTime(int hours, int minutes) {
        this.hours = (this.hours + hours) % 12;
        this.minutes = (this.minutes + minutes) % 60;
    }
}
