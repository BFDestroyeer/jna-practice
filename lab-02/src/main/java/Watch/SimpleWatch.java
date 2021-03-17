package Watch;

import AlarmClock.IAlarmClock;

import java.util.LinkedList;

/**
 * Watches with hour and minute hands
 */
public class SimpleWatch implements IWatch {
    private final String name;
    private final double price;

    protected int hours;
    protected int minutes;

    protected LinkedList<IAlarmClock> alarmClocks;

    public SimpleWatch(String name, double price) {
        this.name = name;
        this.price = price;
        this.hours = 0;
        this.minutes = 0;
        alarmClocks = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void setHours(int hours) throws Exception {
        if (hours < 0 || hours > 23) {
            throw new Exception("Invalid hours");
        }
        this.hours = hours % 12;
        checkAlarms();
    }

    @Override
    public void setMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Invalid minutes");
        }
        this.minutes = minutes;
        checkAlarms();
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void addHours(int hours) throws Exception {
        this.hours = (this.hours + hours) % 12;
        checkAlarms();
    }

    @Override
    public void addMinutes(int minutes) throws Exception{
        addHours((this.minutes + minutes) / 60);
        this.minutes = (this.minutes + minutes) % 60;
        checkAlarms();
    }

    @Override
    public void addSeconds(int seconds) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public int getHours() {
        return this.hours;
    }

    @Override
    public int getMinutes() {
        return this.minutes;
    }

    @Override
    public int getSeconds() throws Exception {
        throw new Exception("Not implemented");
    }

    public String toString() {
        return this.hours + ":" + this.minutes;
    }

    public void pushAlarmClock(IAlarmClock alarmClock) {
        alarmClocks.push(alarmClock);
    }

    public Boolean checkAlarms() {
        for (IAlarmClock alarmClock: this.alarmClocks) {
            try {
                if (alarmClock.getAlarmHours() == this.hours && alarmClock.getAlarmMinutes() == this.minutes) {
                    return true;
                }
            } catch (Exception e)
            {

            }
        }
        return false;
    }
}
