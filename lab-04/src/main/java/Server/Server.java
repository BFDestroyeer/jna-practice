package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server {
    int port = 8080;
    InetAddress host = null;
    ServerSocket serverSocket = null;

    ServerModel serverModel = new ServerModel();

    public Server() {
        try {
            host = InetAddress.getLocalHost();
        } catch(UnknownHostException e) { };

    }

    public void run() {
        try {
            this.serverSocket = new ServerSocket(port, 0, host);
            while (true) {
                Socket socket = this.serverSocket.accept();
                new ServerController(socket, this.serverModel);
            }
        } catch (IOException e) { };
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
