package Client;

import javax.swing.*;

public class Client {
    public ClientController clientController = new ClientController();

    public static void main(String[] args) {
        Client client = new Client();
        ClientWindow window = new ClientWindow(client.clientController);
        JFrame jframe = new JFrame("World time");
        jframe.setContentPane(window.getJPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
