package AlarmClock;

public class AdvancedAlarmClock extends SimpleAlarmClock {
    private int seconds;

    public AdvancedAlarmClock() {
        seconds = 0;
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
