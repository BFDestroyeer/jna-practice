package Server;

import AlarmClock.AdvancedAlarmClock;
import AlarmClock.IAlarmClock;
import Event.*;
import Hibernate.DatabaseProvider;
import Hibernate.DatabaseSessionFactory;
import Watch.BWatch;
import Watch.IWatch;
import Watch.WatchController;
import Watch.WatchType;
import org.hibernate.Session;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class ServerModel implements IPublisher, IListener {
    private EventManager eventManager = new EventManager();

    private DatabaseProvider databaseProvider = new DatabaseProvider();

    private IWatch watch = BWatch.build(WatchType.AdvancedWatch, "", 0);
    private WatchController watchController =  new WatchController(this.watch);
    private LinkedList<IAlarmClock> alarmClocks = new LinkedList<>();

    ServerModel() {
        watchController.addListener(this);
    }

    @Override
    public void addListener(IListener listener) {
        eventManager.subscribe(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        eventManager.unsubscribe(listener);
    }

    public void addAlarmClock(TimeEvent event) {
        AdvancedAlarmClock alarmClock = new AdvancedAlarmClock();
        try {
            alarmClock.setAlarmHours(event.getHours());
            alarmClock.setAlarmMinutes(event.getMinutes());
            alarmClock.setAlarmSeconds(event.getSeconds());
        } catch (Exception e) { };
        try {
            TimeEvent alarmArmedEvent = new TimeEvent(EventType.ALARM_CLOCK_ARMED, event.getHours(),
                    event.getMinutes(), event.getSeconds());
            alarmClocks.push(alarmClock);
            alarmClock.addListener(this);
            watchController.addListener(alarmClock);
            databaseProvider.insertAlarm(alarmClock);
            eventManager.broadcast(alarmArmedEvent);
        } catch (Exception e) { }

    }

    public void fetchAlarms() {
        Session session = DatabaseSessionFactory.get().openSession();
        List<AdvancedAlarmClock> rawAlarms = session.createQuery("from AdvancedAlarmClock", AdvancedAlarmClock.class).getResultList();
        alarmClocks.clear();
        if (rawAlarms != null) {
            for (AdvancedAlarmClock alarmClock : rawAlarms) {
                alarmClocks.push(alarmClock);
                alarmClock.addListener(this);
                watchController.addListener(alarmClock);
            }
        }
        session.close();
    }

    public LinkedList<TimeEvent> getAlarmClocksArmedList() {
        LinkedList<TimeEvent> result = new LinkedList<>();
        for (IAlarmClock alarmClock : this.alarmClocks) {
            try {
                TimeEvent event = new TimeEvent(EventType.ALARM_CLOCK_ARMED, alarmClock.getAlarmHours(),
                        alarmClock.getAlarmMinutes(), alarmClock.getAlarmSeconds());
                result.push(event);
            } catch (Exception e) { };
        }
        return result;
    }

    public TimeEvent getTimeUpdateEvent() {
        return watch.getTimeUpdateEvent();
    }

    public void start() {
        watchController.setEnabled();
    }

    public void pause() {
        watchController.setDisabled();
    }

    public void reset() {
        watchController.reset();
    }

    public void timeUpdate(TimeEvent event) {
        try {
            watch.setHours(event.getHours());
            watch.setMinutes(event.getMinutes());
            watch.setSeconds(event.getSeconds());
        } catch (Exception e) { }
        TimeEvent timeEvent = watch.getTimeUpdateEvent();
        eventManager.broadcast(timeEvent);
    }

    @Override
    public void signal(AbstractEvent event) {
        if (event.type == EventType.TIME_UPDATE || event.type == EventType.ALARM) {
            eventManager.broadcast(event);
        }
    }

}
