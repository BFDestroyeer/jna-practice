package Server;

import AlarmClock.AdvancedAlarmClock;
import Event.*;

import java.io.*;
import java.net.Socket;
import java.sql.Time;

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
                    serverModel.start();
                } else if (event.type == EventType.REQUEST_RESET) {
                    serverModel.reset();
                } else if (event.type == EventType.REQUEST_PAUSE) {
                    serverModel.pause();
                }
            }
        } catch (IOException e) { };
    }

    public void sendEvent(AbstractEvent event) {

    }

    @Override
    public void signal(AbstractEvent event) {
        sendEvent(event);
    }

    private void onRequestAddAlarmClock(TimeEvent event) {
        AdvancedAlarmClock alarmClock = new AdvancedAlarmClock();
        try {
            alarmClock.setAlarmHours(event.getHours());
            alarmClock.setAlarmMinutes(event.getMinutes());
            alarmClock.setAlarmSeconds(event.getSeconds());
        } catch (Exception e) { };
        serverModel.pushAlarmClock(alarmClock);
    }
}
