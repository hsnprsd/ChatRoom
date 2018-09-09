package ir.hsnprsd.chatroom.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;

    private HashMap<User, UserListener> users = new HashMap<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            try {
                addNewUser(socket);
            } catch (IOException ignored) {
            }
        }
    }

    private void addNewUser(Socket socket) throws IOException {
        int id = users.size() + 1;
        User user = new User(id, socket);
        UserListener listener = new UserListener(this, user);
        new Thread(listener::listen).start();
        users.put(user, listener);
    }

    public void sendToOthers(User owner, String message) {
        String actualMessage = owner.getUsername() + " says: " + message;
        for (User user : users.keySet()) {
            if (user.getId() == owner.getId()) {
                UserListener listener = users.get(user);
                try {
                    listener.sendMessage(actualMessage);
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server(8000).listen();
    }
}
