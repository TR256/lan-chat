package chat;

import pojo.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ChatHandler {

    private static DatagramSocket socket;
    private static ByteArrayOutputStream baos;
    private static ObjectOutputStream oos;

    static {
        try {
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(Message message) throws Exception {
        //
        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(message);
        byte[] bytes = baos.toByteArray();
        //
        int len = bytes.length;
        InetAddress address = InetAddress.getByName("255.255.255.255");
        DatagramPacket packet = new DatagramPacket(bytes, len, address, 8633);
        oos.flush();
        socket.send(packet);
    }
}
