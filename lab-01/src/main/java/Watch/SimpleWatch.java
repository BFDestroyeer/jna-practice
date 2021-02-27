package Watch;

/**
 * Watches with hour and minute hands
 */
public class SimpleWatch extends Watch.AbstractWatch {
    private int hours;
    private int minutes;

    public SimpleWatch(String name, double price) {
        super(name, price);
        hours = 0;
        minutes = 0;
    }

    public void SetTime(int hours, int minutes) throws Exception {
        if ((hours < 0) || (hours > 23)) {
            throw new Exception("Invalid hours value");
        }
        if ((minutes < 0) || (minutes > 59)) {
            throw new Exception("Invalid minutes value");
        }
        this.hours = hours;
        this.minutes = minutes;
    }
}
