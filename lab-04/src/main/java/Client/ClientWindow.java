package Client;

import javax.swing.*;

public class ClientWindow {
    private JButton stopButton;
    private JButton button_start;
    private JButton pauseButton;
    private JButton button_connect;
    private JPanel jpanel;

    ClientController clientController;

    ClientWindow(ClientController clientController) {
        this.clientController = clientController;

        button_connect.addActionListener(e -> this.clientController.connect());
        button_start.addActionListener(e -> this.clientController.requestStart());
    }

    public JPanel getJPanel() {
        return this.jpanel;
    }
}
