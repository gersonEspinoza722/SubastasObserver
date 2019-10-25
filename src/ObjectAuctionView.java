import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class ObjectAuctionView{


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AuctionFrame mimarco=new AuctionFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



class AuctionFrame extends JFrame{

    public AuctionFrame(){

        setBounds(600,300,280,350);
        AuctionPanel milamina=new AuctionPanel();

        add(milamina);

        setVisible(true);


    }

}

class AuctionPanel extends JPanel implements Runnable, IOferente, IMessage{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public AuctionPanel(){

        JLabel texto=new JLabel("OFERTAR A SUBASTA EXISTENTE");
        add(texto);

        campo1=new JTextField(20);
        add(campo1);
        campoSub=new JTextField(20);
        add(campoSub);
        respuesta=new JTextField(50);
        add(respuesta);
        ip=new JTextField(15);
        add(ip);

        sendButton=new JButton("Enviar Oferta");
        SendOffer sendOfferEvent = new SendOffer();

        sendButton.addActionListener(sendOfferEvent);
        add(sendButton);


        Thread thread =  new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        //Pasan varas en el hilo
        try {
            ServerSocket server = new ServerSocket(9030);

            while (true) {
                Socket socket = server.accept();


                InputStream streamFromClient = socket.getInputStream();
                ObjectInputStream objectStreamFromClient = new ObjectInputStream(streamFromClient);

                //validacion de objetos
                ISubasta input = null;
                try {
                    input = (ISubasta) objectStreamFromClient.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ISubasta inputO = (ISubasta) input;

                streamFromClient.close();
                socket.close();
                if (inputO != null && ((Subasta) inputO).getStatus() == 0){
                    respuesta.setText("TOPE ACTUAL: " + inputO.getTope() +" ERES EL GANADOR!!!");
                }
                if (inputO != null && ((Subasta) inputO).getStatus() == 2) {
                    respuesta.setText("ALERTA SUBASTA CANCELADA");
                }
                if (inputO != null && ((Subasta) inputO).getStatus() == 3) {
                    respuesta.setText("TOPE ACTUAL: " + inputO.getTope() + "de SUBASTA: " + inputO.getId());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ISubasta getSubasta() {
        return subasta;
    }

    public void setSubasta(ISubasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void notifyObservable() {

        IMessage ofertaMessage;
        ofertaMessage = null;
        Subasta sub = new Subasta();
        sub.setId(Integer.valueOf(campoSub.getText()));
        try {
            ofertaMessage= new OfertaMessage(sub, Integer.valueOf(campo1.getText()), InetAddress.getLocalHost().getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        try {
                Socket socket=new Socket(ip.getText(),88);
               // DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(ofertaMessage);

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public void unirSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void ofrecerMonto() {
        notifyObservable();
    }

    @Override
    public int getMonto() {
        return Integer.valueOf(campo1.getText());
    }

    @Override
    public String getIp() {
        return this.usuario.ip;
    }


    private class SendOffer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ofrecerMonto();
        }
    }


    private JTextField campo1;
    private JTextField campoSub;
    private JTextField respuesta;
    private JTextField ip;
    private JButton sendButton;

}