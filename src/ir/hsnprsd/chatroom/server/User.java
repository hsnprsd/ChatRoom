package ir.hsnprsd.chatroom.server;

import java.net.Socket;

public class User {
    private int id;
    private Socket socket;
    private String username;

    public User(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
        username = "Guest" + id;
    }

    public int getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
