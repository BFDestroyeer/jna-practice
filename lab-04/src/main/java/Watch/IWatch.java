package Watch;

import Event.TimeEvent;

public interface IWatch {
    void setHours(int hours) throws Exception;
    void setMinutes(int minutes) throws Exception;
    void setSeconds(int seconds) throws Exception;

    void addHours(int hours) throws Exception;
    void addMinutes(int minutes) throws Exception;
    void addSeconds(int seconds) throws Exception;

    int getHours() throws Exception;
    int getMinutes() throws Exception;
    int getSeconds() throws Exception;

    int getTickTime();
    void doTick();

    TimeEvent getTimeUpdateEvent();
}
