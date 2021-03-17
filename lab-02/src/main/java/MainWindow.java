import javax.swing.*;

import AlarmClock.AdvancedAlarmClock;
import Watch.AdvancedWatch;
import Watch.BWatchWithAlarmClock;
import Watch.IWatchWithAlarmClock;
import Watch.WatchType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JButton button_setTime;
    private JPanel mainPanel;
    private JSpinner spinner_hours;
    private JSpinner spinner_minutes;
    private JSpinner spinner_seconds;
    private JLabel label_time;
    private JSpinner spinner_alarmSeconds;
    private JSpinner spinner_alarmMinutes;
    private JSpinner spinner_alarmHours;
    private JButton button_pushAlarmClock;

    private final IWatchWithAlarmClock watches;

    public MainWindow() {
        watches = BWatchWithAlarmClock.build(WatchType.AdvancedWatch, "MyWatch", 10);
        updateTimeLabel();

        button_setTime.addActionListener(e -> updateTime());
        button_pushAlarmClock.addActionListener(e -> pushAlarmClock());
    }

    public void updateTime() {
        try {
            watches.setHours((Integer) spinner_hours.getValue());
            watches.setMinutes((Integer) spinner_minutes.getValue());
            watches.setSeconds((Integer) spinner_seconds.getValue());
        } catch (Exception e) {
            return;
        }
        updateTimeLabel();
        if (watches.checkAlarms()) {
            showAlarmDialog();
        }
    }

    public void pushAlarmClock() {
        AdvancedAlarmClock alarmClock = new AdvancedAlarmClock();
        try {
            alarmClock.setAlarmHours((Integer) spinner_alarmHours.getValue());
            alarmClock.setAlarmMinutes((Integer) spinner_alarmMinutes.getValue());
            alarmClock.setAlarmSeconds((Integer) spinner_alarmSeconds.getValue());
        } catch (Exception e) {
            return;
        }
        watches.pushAlarmClock(alarmClock);
    }

    public void updateTimeLabel() {
        label_time.setText(watches.toString());
    }

    public void showAlarmDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "ALARM!!!");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Watches");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
