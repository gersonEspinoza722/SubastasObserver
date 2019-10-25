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
        subastas = new ArrayList<>();
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

    public void notifyWinner(ISubasta subastaNotify){
        try {
            OfertaMessage oferta = (OfertaMessage) subastaNotify.getOfertas().get(subastaNotify.getOfertas().size()-1);
            Socket socket=new Socket(oferta.getIp(),9090);
            OutputStream streamToServer=socket.getOutputStream();
            ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);
            ((Subasta)subastaNotify).setStatus(0);

            objectStreamToServer.writeObject(subastaNotify);
            socket.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

                Socket socket = server.accept();

                InputStream streamFromClient = socket.getInputStream();
                ObjectInputStream objectStreamFromClient = new ObjectInputStream(streamFromClient);


                IObserver input = null;

                try {
                    input = (IObserver) objectStreamFromClient.readObject();
                    //System.out.println("Encontro el objeto");
                    //System.out.println("TIPO: "+input.getType());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                IMessage inputO = (IMessage) input;
                if(input.getType() == 0){//ENTRA PARA PUJAR EN SUBASTA

                    for(int i = 0; i<this.subastas.size(); i++){
                        ISubasta subastaTemp = inputO.getSubasta();
                        if(subastaTemp.getId() == subastas.get(i).getId() && subastas.get(i).getTope() <  ((OfertaMessage)inputO).getMonto()){
                            System.out.println("Encontro la subasta para pujar");
                            subastas.get(i).setTope(((OfertaMessage)inputO).getMonto());
                            subastas.get(i).addOferta(inputO);
                            areatexto.setText(Integer.toString(subastas.get(i).getTope()));
                            notifyAllOferentes(subastas.get(i));

                        }
                    }
                }
                if(input.getType() == 1){ //ENTRA CUANDO LOGGEA OFERENTE A SUBASTA
                    System.out.println("Entro a loggear");

                    //areatexto.setText(String.valueOf(inputO.getSubasta().getId()));
                   for(int i = 0; i<this.subastas.size(); i++){
                        ISubasta subastaTemp = inputO.getSubasta();
                        if(subastaTemp.getId() == subastas.get(i).getId()){
                            subastas.get(i).addOferente(((IOferente)((LogInMessage) inputO).usuario));
                            System.out.println(((IOferente)((LogInMessage) inputO).usuario).getIp());
                            areatexto.setText(((IOferente)((LogInMessage) inputO).usuario).getIp()+"  Oferentes:"+subastas.get(i).getOferentes().size()+" de la subasta"+ subastas.get(i).getId());
                            //notifyAllOferentes(subastas.get(i));

                        }
                    }
                }
                if(input.getType() == 2){ //ENTRA PARA CREAR SUBASTA CON SUBASTADOR
                    //areatexto.setText(String.valueOf(inputO.getSubasta().getId()));
                    System.out.println("Entro a crear subasta");
                        ISubasta subastaTemp = (Subasta)((CreateAuctionMessage)inputO).getSubasta();
                        subastas.add(subastaTemp);


                        areatexto.setText(subastaTemp.getSubastador().getIp()+" SUBASTA: " + subastaTemp.getId());
                        //notifyAllOferentes(subastas.get(i));

                }
                if(input.getType() == 3){ //ENTRA rechazo de oferta

                    System.out.println("Entro a rechazo");

                    for(int i = 0; i<this.subastas.size(); i++){
                        ISubasta subastaTemp =  (Subasta)((RechazoMessage)inputO).getSubasta();
                        if(subastaTemp.getId() == subastas.get(i).getId()){
                            //System.out.println("Encontró subasta para rechazar");
                            subastas.get(i).getOfertas().remove(subastas.get(i).getOfertas().size()-1);
                            subastas.get(i).setTope(((OfertaMessage)subastas.get(i).getOfertas().get(subastas.get(i).getOfertas().size()-1)).getMonto());

                            areatexto.setText(String.valueOf(subastas.get(i).getOfertas().size()));
                            notifyAllOferentes(subastas.get(i));

                        }
                    }

                }
                if(input.getType() == 4){ //ENTRA para terminar

                    System.out.println("Entro a fin");

                    for(int i = 0; i<this.subastas.size(); i++){
                        ISubasta subastaTemp =  (Subasta)((CerrarMessage)inputO).getSubasta();
                        if(subastaTemp.getId() == subastas.get(i).getId()){
                            //System.out.println("Encontró subasta para rechazar");

                            notifyWinner(subastas.get(i));

                            areatexto.setText(String.valueOf(subastas.get(i).getOfertas().size()));
                            notifyAllOferentes(subastas.get(i));

                        }
                    }

                }

                else {

                }
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
