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



class AuctionFrame extends JFrame implements Runnable{

    public AuctionFrame(){

        setBounds(600,300,280,350);
        AuctionPanel milamina=new AuctionPanel();

        add(milamina);

        setVisible(true);

        Thread thread =  new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        //Pasan varas en el hilo
        try {
            ServerSocket server = new ServerSocket(89);

            while (true) {
                Socket socket = server.accept();

                DataInputStream streamFromClient = new DataInputStream(socket.getInputStream());

                String input = streamFromClient.readUTF();

                streamFromClient.close();
                System.out.println(input);

                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class AuctionPanel extends JPanel implements IOferente{
    private Usuario usuario;
    private Subasta subasta;
    private static final int type = 0;

    public AuctionPanel(){

        JLabel texto=new JLabel("CLIENTE");
        add(texto);

        campo1=new JTextField(20);
        add(campo1);
        sendButton=new JButton("Enviar");
        SendOffer sendOfferEvent = new SendOffer();
        sendButton.addActionListener(sendOfferEvent);
        add(sendButton);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void notifyObservable() {
        if(!campo1.getText().isEmpty()){
            int value = Integer.valueOf(campo1.getText());

            try {
                Socket socket=new Socket(serverIP,88);
               // DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(this);

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getIp() {
        return usuario.ip;
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


    private class SendOffer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ofrecerMonto();
        }
    }


    private JTextField campo1;
    private JButton sendButton;
    private String serverIP;

}