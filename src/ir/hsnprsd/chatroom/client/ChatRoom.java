package ir.hsnprsd.chatroom.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private boolean connected = true;

    private Communicator communicator;

    private List<String> messages = new ArrayList<>();

    public ChatRoom(String host, int port) throws IOException {
        communicator = new Communicator(this, host, port);
        new Thread(() -> communicator.listen());
    }

    public List<String> getMessages() {
        return messages;
    }

    public void newMessage(String message) {
        messages.add(message);
    }

    public boolean isConnected() {
        return connected;
    }

    public void exit() {
        connected = false;
    }
}
