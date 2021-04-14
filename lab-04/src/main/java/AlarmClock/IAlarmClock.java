package AlarmClock;

public interface IAlarmClock {
    public void setAlarmHours(int hours) throws Exception;
    public void setAlarmMinutes(int hours) throws Exception;
    public void setAlarmSeconds(int hours) throws Exception;

    public int getAlarmHours() throws Exception;
    public int getAlarmMinutes() throws Exception;
    public int getAlarmSeconds() throws Exception;

}
