import javax.swing.*;

import AlarmClock.AdvancedAlarmClock;
import AlarmClock.IAlarmClock;
import Events.AbstractEvent;
import Events.ICallable;
import Events.IPublisher;
import Watch.*;
import Watch.BWatch;
import Events.IListener;

import java.util.LinkedList;

public class MainWindow implements IListener {
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
    private JButton button_start;
    private JButton button_stop;
    private JButton button_pause;
    private JLabel label_lastAlarm;

    private final IWatch watch;
    private final WatchController controller;
    private LinkedList<IAlarmClock> alarms = new LinkedList<>();
    private ICallable alarm_slot;

    public MainWindow() {
        this.watch = BWatch.build(WatchType.AdvancedWatch, "MyWatch", 10);
        this.controller = new WatchController(this.watch);

        IPublisher publisher = (IPublisher) watch;
        publisher.addListener(this);
        alarm_slot = () -> {
            String label_text = "";
            try {
                label_text = watch.getHours() + ":" + watch.getMinutes();
                label_text +=  ":" + watch.getSeconds();
            } catch (Exception e) { };
            this.label_lastAlarm.setText(label_text);
        };

        button_setTime.addActionListener(e -> updateTime());
        button_pushAlarmClock.addActionListener(e -> pushAlarmClock());
        button_pause.addActionListener(e -> this.controller.setDisabled());
        button_start.addActionListener(e -> this.controller.setEnabled());
        button_stop.addActionListener(e -> this.controller.reset());
    }

    @Override
    public void signal(AbstractEvent event) {
        TimeEvent timeEvent = (TimeEvent) event;
        String label_text = timeEvent.getHours() + ":" + timeEvent.getMinutes() + ":" + timeEvent.getSeconds();
        this.label_time.setText(label_text);
    }

    @Override
    public void connect(ICallable slot) {
    }

    public void updateTime() {
        try {
            this.watch.setHours((Integer) spinner_hours.getValue());
            this.watch.setMinutes((Integer) spinner_minutes.getValue());
            this.watch.setSeconds((Integer) spinner_seconds.getValue());
        } catch (Exception e) { }
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
        IPublisher publisher = (IPublisher) watch;
        ((IListener) alarmClock).connect(alarm_slot);
        publisher.addListener((IListener) alarmClock);
        alarms.push(alarmClock);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Watches");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
