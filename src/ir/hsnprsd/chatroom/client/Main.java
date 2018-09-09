package ir.hsnprsd.chatroom.client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ChatRoom chatRoom = new ChatRoom("localhost", 8000);
        ChatRoomView chatRoomView = new ChatRoomView(chatRoom);
        chatRoomView.setVisible(true);
    }
}
