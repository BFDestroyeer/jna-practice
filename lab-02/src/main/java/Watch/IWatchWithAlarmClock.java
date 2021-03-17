package Watch;

import AlarmClock.IAlarmClock;

public interface IWatchWithAlarmClock extends IWatch {
    void pushAlarmClock(IAlarmClock alarmClock);
    Boolean checkAlarms();
}
