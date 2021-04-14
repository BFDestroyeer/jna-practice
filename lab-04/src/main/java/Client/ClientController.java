package Client;

import Event.AbstractEvent;
import Event.EventManager;
import Event.EventType;
import Event.IListener;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {
    private EventManager eventManager = new EventManager();

    Thread thread;

    int port = 8080;
    InetAddress host = null;

    Socket socket;

    InputStream inputStream;
    OutputStream outputStream;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void connect() {
        try {
            this.host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) { }
        try {
            socket = new Socket(this.host, this.port);

            this.outputStream = socket.getOutputStream();
            this.dataOutputStream = new DataOutputStream(this.outputStream);

            this.thread = new Thread(this::run);
            this.thread.start();
        } catch (IOException e) { };
    }

    public void requestStart() {
        AbstractEvent event = new AbstractEvent(EventType.REQUEST_START);
    }

    private void send(String data) {
        if (thread == null) {
            return;
        }
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) { };
    }

    private  void run() {
        try {
            this.inputStream = socket.getInputStream();
            this.dataInputStream = new DataInputStream(this.inputStream);
            while (true) {
                String data = this.dataInputStream.readUTF();
                // TODO: Event
            }
        } catch (IOException e) { };
    }
}
