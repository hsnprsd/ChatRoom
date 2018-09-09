package ir.hsnprsd.chatroom.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Communicator {
    private ChatRoom chatRoom;

    private Socket socket;

    private DataOutputStream out;
    private DataInputStream in;

    public Communicator(ChatRoom chatRoom, String host, int port) throws IOException {
        this.chatRoom = chatRoom;

        socket = new Socket(host, port);

        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    public boolean sendMessage(String message) {
        try {
            out.writeUTF(message);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void listen() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = in.readUTF();
                chatRoom.newMessage(message);
            } catch (IOException e) {
                chatRoom.exit();
            }
        }
    }
}
