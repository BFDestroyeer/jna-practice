package Watch;

/**
 * Watches with hour, minute and second hands
 */
public class AdvancedWatch extends Watch.SimpleWatch {
    protected int seconds;

    public AdvancedWatch(String name, double price) {
        super(name, price);
        this.seconds = 0;
    }

    public void setTime(int hours, int minutes, int seconds) throws Exception{
        if ((seconds < 0) || (seconds > 59)) {
            throw new Exception("Invalid seconds value");
        }
        setTime(hours, minutes);
        this.seconds = seconds;
    }

    public void addTime(int hours, int minutes, int seconds) {
        addTime(hours, minutes);
        this.seconds = (this.seconds + seconds) % 60;
    }
}
