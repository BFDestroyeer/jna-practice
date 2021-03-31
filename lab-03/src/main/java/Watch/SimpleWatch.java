package Watch;

import Events.EventManager;
import Events.IListener;
import Events.IPublisher;

/**
 * Watches with hour and minute hands
 */
public class SimpleWatch implements IWatch {
    private final String name;
    private final double price;

    protected int hours;
    protected int minutes;

    public SimpleWatch(String name, double price) {
        this.name = name;
        this.price = price;
        this.hours = 0;
        this.minutes = 0;
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
    }

    @Override
    public void setMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Invalid minutes");
        }
        this.minutes = minutes;
    }

    @Override
    public void setSeconds(int seconds) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public void addHours(int hours) throws Exception {
        this.hours = (this.hours + hours) % 12;
    }

    @Override
    public void addMinutes(int minutes) throws Exception{
        addHours((this.minutes + minutes) / 60);
        this.minutes = (this.minutes + minutes) % 60;
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

    @Override
    public boolean hasSecondsHand() {
        return false;
    }
}
