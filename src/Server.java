import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ServerFrame mimarco=new ServerFrame();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ServerFrame extends JFrame implements Runnable, IObservable{
    ArrayList<ISubasta> subastas;

    public ServerFrame(){

        //this.observers = new ArrayList<>();

        setBounds(1200,300,280,350);

        JPanel panel= new JPanel();
        panel.setLayout(new BorderLayout());

        areatexto=new JTextArea();
        panel.add(areatexto,BorderLayout.CENTER);
        add(panel);
        setVisible(true);
        //notifyAllOferentes();
        Thread thread =  new Thread(this);
        thread.start();

    }

    @Override
    public void notifyAllOferentes() {


        for (int i=0; i<subastas.size(); i++){ //hacer for correcto
            //observers.get(i).notifyObservable();
            try {
                Socket socket=new Socket("127.0.0.1",9090);
                //Socket socket2=new Socket("192.168.0.7",9090);
                DataOutputStream streamToOferente = new DataOutputStream(socket.getOutputStream());
                streamToOferente.writeUTF("respuesta"); //implementar respuesta

               // DataOutputStream streamToOferente2 = new DataOutputStream(socket2.getOutputStream());
               //streamToOferente2.writeUTF("respuesta linda"); //implementar respuesta

               // socket2.close();
               // streamToOferente2.close();
                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



    @Override
    public void addObserver(IObserver observer, int id) {
        for (int i = 0; i<subastas.size(); i++){
            subastas.get(i).addOferente((IOferente) observer);
        }
    }

    @Override
    public void removeObserver(IObserver observer, int id) {
        for (int i = 0; i<subastas.size(); i++){
            subastas.get(i).removeOferente((IOferente) observer);
        }
    }

    private	JTextArea areatexto;

    @Override
    public void run() {
        //Pasan varas en el hilo
        try {
            ServerSocket server = new ServerSocket(88);

            while (true) {
                //System.out.println("while");
                Socket socket = server.accept();

                InputStream streamFromClient = socket.getInputStream();
                ObjectInputStream objectStreamFromClient = new ObjectInputStream(streamFromClient);


                IObserver input = null;

                try {
                    input = (IObserver) objectStreamFromClient.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if(input.getType() == 0){
                    IOferente inputO = (IOferente) input;
                    areatexto.setText(Integer.toString(inputO.getMonto()));
                    notifyAllOferentes();
                }else{

                }

                socket.close();





            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
