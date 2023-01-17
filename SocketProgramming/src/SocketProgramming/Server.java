package SocketProgramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6000);
            Socket s = ss.accept();

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            dout.writeByte(1);
            dout.writeUTF(String.valueOf(Runtime.getRuntime().availableProcessors()));
            dout.flush(); // Send off the data

            // Send the second message
            dout.writeByte(2);
            dout.writeUTF(String.valueOf(Runtime.getRuntime().freeMemory()));
            dout.flush(); // Send off the data

            // Send the exit message
            dout.writeByte(-1);
            dout.flush();

            dout.close();

            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
