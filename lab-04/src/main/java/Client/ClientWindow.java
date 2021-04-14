package Client;

import javax.swing.*;

public class ClientWindow {
    private JButton button_reset;
    private JButton button_start;
    private JButton button_pause;
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
