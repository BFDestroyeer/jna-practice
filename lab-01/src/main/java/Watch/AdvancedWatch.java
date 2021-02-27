package Watch;

/**
 * Watches with hour, minute and second hands
 */
public class AdvancedWatch extends Watch.SimpleWatch {
    private int seconds;

    public AdvancedWatch(String name, double price) {
        super(name, price);
        seconds = 0;
    }

    public void SetTime(int hours, int minutes, int seconds) throws Exception{
        if ((seconds < 0) || (seconds > 56)) {
            throw new Exception("Invalid seconds value");
        }
        SetTime(hours, minutes);
        this.seconds = 0;
    }
}
