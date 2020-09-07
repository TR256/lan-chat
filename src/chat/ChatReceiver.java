package chat;

import pojo.Message;
import window.ChatWindow;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatReceiver implements Runnable {

    private DatagramSocket socket;

    public ChatReceiver() {
        try {
            socket = new DatagramSocket(8633);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 接收消息
     */
    private void receive() {
        try {
            byte[] bytes = new byte[1024 * 16];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            //
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            //
            Message message = (Message) ois.readObject();
            System.out.println(message);
            ChatWindow.getInstance().displayMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            receive();
        }
    }
}
