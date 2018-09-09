package ir.hsnprsd.chatroom.client;

import javax.swing.*;

public class ChatRoomView extends JFrame {
    private ChatRoom chatRoom;

    private JPanel messagesPanel;
    private JPanel actionPanel;

    public ChatRoomView(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;

        init();

        new Thread(() -> {
            while (chatRoom.isConnected()) {
                update();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    private void init() {
        setTitle("Chat Room");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.PAGE_AXIS));

        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.LINE_AXIS));

        JTextField messageField = new JTextField();
        actionPanel.add(messageField);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener((e) -> {
        });

        getContentPane().add(messagesPanel);
        getContentPane().add(actionPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update() {
        messagesPanel.removeAll();
        for (String message: chatRoom.getMessages()) {
            messagesPanel.add(new MessageView(message));
        }
    }
}
