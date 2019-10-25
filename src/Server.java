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
    public void notifyAllOferentes(ISubasta subastaNotify) {

        ArrayList<IOferente> oferentesNotify = subastaNotify.getOferentes();
        for (int i=0; i<oferentesNotify.size(); i++){ //hacer for correcto
            try {
                Socket socket=new Socket(oferentesNotify.get(i).getIp(),9090);
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(subastaNotify);
                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        ISubastador subastadorNotify = subastaNotify.getSubastador();
        try {
            Socket socket=new Socket(subastadorNotify.getIp(),9050);
            OutputStream streamToServer=socket.getOutputStream();
            ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

            objectStreamToServer.writeObject(subastaNotify);
            socket.close();


        } catch (IOException ex) {
            ex.printStackTrace();
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

                IMessage inputO = (IMessage) input;
                if(input.getType() == OfertaMessage.class){
                    for(int i = 0; i<this.subastas.size(); i++){
                        ISubasta subastaTemp = ((IMessage) input).getSubasta();
                        if(subastaTemp.getId() == subastas.get(i).getId() && subastas.get(i).getTope() < subastaTemp.getTope()){
                            subastas.get(i).setTope(subastaTemp.getTope());
                            subastas.get(i).addOferta(inputO);
                            areatexto.setText(Integer.toString(inputO.getSubasta().getTope()));
                            notifyAllOferentes(subastas.get(i));
                            break;
                        }
                    }
                }else {

                }
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
