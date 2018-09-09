package ir.hsnprsd.chatroom.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserListener {
    private Socket socket;

    private DataInputStream in;
    private DataOutputStream out;

    public UserListener(Server server, User user) throws IOException {
        socket = user.getSocket();
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public void listen() {
        while (socket.isConnected() && !Thread.currentThread().isInterrupted()) {
            String message = null;
            try {
                message = in.readUTF();
            } catch (IOException e) {
                break;
            }
            if (message.equals("exit")) {
                break;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }
}
