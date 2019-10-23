import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IObservable{
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ServerFrame mimarco=new ServerFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    @Override
    public void notifyAllOferentes() {
        //implementar
    }

    @Override
    public void addObserver(IObserver observer) {
        //implementar
    }

    @Override
    public void removeObserver(IObserver observer) {
        //implementar
    }
}

class ServerFrame extends JFrame implements Runnable {

    public ServerFrame(){

        setBounds(1200,300,280,350);

        JPanel panel= new JPanel();

        panel.setLayout(new BorderLayout());

        areatexto=new JTextArea();

        panel.add(areatexto,BorderLayout.CENTER);

        add(panel);

        setVisible(true);

        Thread thread =  new Thread(this);
        thread.start();

    }

    private	JTextArea areatexto;

    @Override
    public void run() {
        //Pasan varas en el hilo
        try {
            ServerSocket server = new ServerSocket(88);

            while (true) {
                Socket socket = server.accept();

                DataInputStream streamFromClient = new DataInputStream(socket.getInputStream());

                String input = streamFromClient.readUTF();

                streamFromClient.close();
                areatexto.setText(input);

                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
