package Server;

import AlarmClock.AdvancedAlarmClock;
import Event.*;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.LinkedList;

public class ServerController extends Thread implements IListener {
    private EventManager eventManager = new EventManager();

    Socket socket;
    ServerModel serverModel;

    InputStream inputStream;
    OutputStream outputStream;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public ServerController(Socket socket, ServerModel serverModel) {
        this.socket = socket;
        this.serverModel = serverModel;

        try {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
        } catch (IOException e) { };

        serverModel.addListener(this);

        LinkedList<TimeEvent> list = serverModel.getAlarmClocksArmedList();
        for (TimeEvent event : list) {
            sendEvent(event);
        }

        start();
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            while (true) {
                String data = dataInputStream.readUTF();
                AbstractEvent event = JSON.get().fromJson(data, AbstractEvent.class);
                if (event.type == EventType.REQUEST_START) {
                    this.serverModel.start();
                    continue;
                } else if (event.type == EventType.REQUEST_RESET) {
                    this.serverModel.reset();
                    continue;
                } else if (event.type == EventType.REQUEST_PAUSE) {
                    this.serverModel.pause();
                    continue;
                }
                TimeEvent timeEvent = JSON.get().fromJson(data, TimeEvent.class);
                if (event.type == EventType.REQUEST_TIME_UPDATE) {
                    this.serverModel.timeUpdate(timeEvent);
                }
                if (event.type == EventType.REQUEST_ADD_ALARM_CLOCK) {
                    this.serverModel.addAlarmClock(timeEvent);
                }
            }
        } catch (IOException e) { };
    }

    public void sendEvent(AbstractEvent event) {
        if (event.type == EventType.TIME_UPDATE || event.type == EventType.ALARM_CLOCK_ARMED) {
            TimeEvent timeEvent = (TimeEvent) event;
            String data = JSON.get().toJson(timeEvent);
            try {
                dataOutputStream.writeUTF(data);
            } catch (IOException e) { }
        } else if (event.type == EventType.ALARM) {
            String data = JSON.get().toJson(event);
            try {
                dataOutputStream.writeUTF(data);
            } catch (IOException e) { }
        }
    }

    @Override
    public void signal(AbstractEvent event) {
        sendEvent(event);
    }
}
