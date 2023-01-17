package SocketProgramming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends JFrame{

    private DataInputStream din;

    private JTextField textField1,textField2;

    private JLabel label1,label2;

    private JButton button;

    public Client(){

        setLayout(null);

        textField1 = new JTextField();
        textField1.setBounds(10,10 , 250, 40);
        add(textField1);

        textField2 = new JTextField();
        textField2.setBounds(10,55,250,40);
        add(textField2);

        button = new JButton("Connect");
        button.setBounds(320,30,100,40);
        add(button);

        label1 = new JLabel();
        label1.setBounds(10, 100,250,40);

        label2 = new JLabel();
        label2.setBounds(10, 145,250,40);



        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==button) {
                    try {
                        Socket s = new Socket(InetAddress.getByName(textField1.getText()),Integer.parseInt(textField2.getText()));
                        //DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        DataInputStream din = new DataInputStream(s.getInputStream());

                        boolean done = false;
                        while(!done) {
                            byte messageType = din.readByte();

                            switch(messageType)
                            {
                                case 1:
                                    add(label1);
                                    label1.setText("Available processors (cores): "+ din.readUTF());
                                    break;
                                case 2:
                                    add(label2);
                                    label2.setText("Free memory (bytes): "+ din.readUTF());
                                    break;
                                default:
                                    done = true;
                            }
                        }

                        din.close();
                        s.close();
                    } catch (Exception exception) {
                        System.out.println(e);
                    }
                }
            }
        });


        setSize(new Dimension(640, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);




    }

    public static void main(String[] args) {

        Client client = new Client();

    }
}
