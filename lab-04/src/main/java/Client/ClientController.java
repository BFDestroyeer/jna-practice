package Client;

import Event.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Time;

public class ClientController implements IPublisher {
    private EventManager eventManager = new EventManager();

    Thread thread;

    int port = 8080;
    InetAddress host = null;

    Socket socket;

    InputStream inputStream;
    OutputStream outputStream;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public void addListener(IListener listener) {
        this.eventManager.subscribe(listener);
    }

    public void removeListener(IListener listener) {
        this.eventManager.unsubscribe(listener);
    }

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
        String data = JSON.get().toJson(event);
        send(data);
    }

    public void requestReset() {
        AbstractEvent event = new AbstractEvent(EventType.REQUEST_RESET);
        String data = JSON.get().toJson(event);
        send(data);
    }

    public void requestPause() {
        AbstractEvent event = new AbstractEvent(EventType.REQUEST_PAUSE);
        String data = JSON.get().toJson(event);
        send(data);
    }

    public void requestSetTime(int hours, int minutes, int seconds) {
        TimeEvent event = new TimeEvent(EventType.REQUEST_TIME_UPDATE, hours, minutes, seconds);
        String data = JSON.get().toJson(event);
        send(data);
    }

    public void requestAddAlarm(int hours, int minutes, int seconds) {
        TimeEvent event = new TimeEvent(EventType.REQUEST_ADD_ALARM_CLOCK, hours, minutes, seconds);
        String data = JSON.get().toJson(event);
        send(data);
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
                TimeEvent event = JSON.get().fromJson(data, TimeEvent.class);
                if (event.type == EventType.TIME_UPDATE || event.type == EventType.ALARM_CLOCK_ARMED) {
                    eventManager.broadcast(event);
                }
            }
        } catch (IOException e) { };
    }
}
