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

    ClientController clientController;

    ClientWindow(ClientController clientController) {
        this.clientController = clientController;
        clientController.addListener(this);

        button_connect.addActionListener(e -> this.clientController.connect());
        button_start.addActionListener(e -> this.clientController.requestStart());
    }

    public void signal(AbstractEvent event) {
        TimeEvent timeEvent = (TimeEvent) event;
        if (timeEvent.type == EventType.TIME_UPDATE) {
            String text = timeEvent.getHours() + ":" + timeEvent.getMinutes() + ":" + timeEvent.getSeconds();
            label_time.setText(text);
        }
    }

    public JPanel getJPanel() {
        return this.jpanel;
    }
}
