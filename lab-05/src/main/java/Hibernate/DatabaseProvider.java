package Hibernate;

import AlarmClock.IAlarmClock;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseProvider {
    public void insertAlarm(IAlarmClock alarmClock) {
        Session session = DatabaseSessionFactory.get().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(alarmClock);
        transaction.commit();
        session.close();
    }

    public void deleteAlarm(IAlarmClock alarmClock) {
        Session session = DatabaseSessionFactory.get().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(alarmClock);
        transaction.commit();
        session.close();
    }
}
