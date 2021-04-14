package AlarmClock;

import Events.AbstractEvent;
import Events.ICallable;
import Events.IListener;
import Watch.TimeEvent;

import java.awt.*;
import java.util.LinkedList;

public class SimpleAlarmClock implements IAlarmClock, IListener {
    protected int hours;
    protected int minutes;
    protected ICallable slot;

    public SimpleAlarmClock() {
        hours = 0;
        minutes = 0;
    }

    @Override
    public void signal(AbstractEvent event) {
        TimeEvent timeEvent = (TimeEvent) event;
        if (timeEvent.getHours() == this.hours && timeEvent.getMinutes() == minutes) {
            slot.call();
        }
    }

    @Override
    public void connect(ICallable slot) {
        this.slot = slot;
    }

    @Override
    public void setAlarmHours(int hours) throws Exception {
        if (hours < 0 || hours > 11) {
            throw new Exception("Invalid hours");
        }
        this.hours = hours % 12;
    }

    @Override
    public void setAlarmMinutes(int minutes) throws Exception {
        if (minutes < 0 || minutes > 59) {
            throw new Exception("Invalid minutes");
        }
        this.minutes = minutes;
    }

    @Override
    public void setAlarmSeconds(int seconds) throws Exception {
        throw new Exception("Not implemented");
    }

    @Override
    public int getAlarmHours() {
        return this.hours;
    }

    @Override
    public int getAlarmMinutes() {
        return this.minutes;
    }

    public int getAlarmSeconds() throws Exception {
        throw new Exception("Not implemented");
    }
}
