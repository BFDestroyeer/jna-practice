package Server;

import AlarmClock.IAlarmClock;
import Event.AbstractEvent;
import Event.EventManager;
import Event.IListener;
import Event.IPublisher;
import Watch.BWatch;
import Watch.IWatch;
import Watch.WatchController;
import Watch.WatchType;

import java.util.LinkedList;

public class ServerModel implements IPublisher, IListener {
    private EventManager eventManager = new EventManager();

    private IWatch watch = BWatch.build(WatchType.AdvancedWatch, "", 0);
    private WatchController watchController =  new WatchController(this.watch);
    private LinkedList<IAlarmClock> alarmClocks = new LinkedList<>();

    @Override
    public void addListener(IListener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        eventManager.unsubscribe(listener);
    }

    public void pushAlarmClock(IAlarmClock alarmClock) {

    }

    @Override
    public void signal(AbstractEvent event) {

    }

}
