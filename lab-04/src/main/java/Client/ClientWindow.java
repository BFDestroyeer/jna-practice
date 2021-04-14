package Client;

import Event.AbstractEvent;
import Event.EventType;
import Event.IListener;
import Event.TimeEvent;

import javax.swing.*;

public class ClientWindow implements IListener {
    private JButton button_reset;
    private JButton button_start;
    private JButton button_pause;
    private JButton button_connect;
    private JPanel jpanel;
    private JLabel label_time;
    private JButton button_setTime;
    private JSpinner spinner_seconds;
    private JSpinner spinner_minutes;
    private JSpinner spinner_hours;

    ClientController clientController;

    ClientWindow(ClientController clientController) {
        this.clientController = clientController;
        clientController.addListener(this);

        button_connect.addActionListener(e -> this.clientController.connect());
        button_start.addActionListener(e -> this.clientController.requestStart());
        button_reset.addActionListener(e -> this.clientController.requestReset());
        button_pause.addActionListener(e -> this.clientController.requestPause());
        button_setTime.addActionListener(e -> this.onSetTime());
    }

    public void signal(AbstractEvent event) {
        TimeEvent timeEvent = (TimeEvent) event;
        if (timeEvent.type == EventType.TIME_UPDATE) {
            String text = timeEvent.getHours() + ":" + timeEvent.getMinutes() + ":" + timeEvent.getSeconds();
            label_time.setText(text);
        }
    }

    public void onSetTime() {
        this.clientController.requestSetTime((int) spinner_hours.getValue(), (int) spinner_minutes.getValue(),
                (int) spinner_seconds.getValue());
    }

    public JPanel getJPanel() {
        return this.jpanel;
    }
}
