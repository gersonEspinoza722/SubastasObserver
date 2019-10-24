import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AuctionerView {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AuctionerFrame mimarco=new AuctionerFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



class AuctionerFrame extends JFrame{

    public AuctionerFrame(){

        setBounds(600,300,280,350);
        AuctionPanel milamina=new AuctionPanel();

        add(milamina);

        setVisible(true);


    }

}

class AuctionerPanel extends JPanel implements ISubastador, Runnable{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public AuctionerPanel(){

        JLabel texto=new JLabel("CLIENTE");
        add(texto);

        campo1=new JTextField(20);
        add(campo1);
        sendButton=new JButton("Enviar");
        SendRechazo sendRechazo = new SendRechazo();
        sendButton.addActionListener(sendRechazo);
        add(sendButton);
        Thread thread =  new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        //Pasan varas en el hilo
        try {
            ServerSocket server = new ServerSocket(9090);

            while (true) {
                Socket socket = server.accept();

                InputStream streamFromClient = socket.getInputStream();
                ObjectInputStream objectStreamFromClient = new ObjectInputStream(streamFromClient);

                //validacion de objetos
                IObservable input = null;
                try {
                    input = (IObservable) objectStreamFromClient.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ISubasta inputO = (ISubasta) input;
                campo1.setText(Integer.toString(inputO.getTope()));
                //notifyAllOferentes();

                streamFromClient.close();
                System.out.println(input);

                socket.close();
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
        if(!campo1.getText().isEmpty()){
            int value = Integer.valueOf(campo1.getText());

            try {
                Socket socket=new Socket(serverIP,88);
                // DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(subasta);

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void rechazarOferta() {
        //le ponemos a "subasta" el id
        notifyObservable();
    }

    @Override
    public void cerrarSubasta() {

    }

    @Override
    public void cancelarSubasta() {

    }

    @Override
    public void enviarMensaje(Usuario usuario) {

    }

    @Override
    public void createAuction() {

    }


    private class SendRechazo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rechazarOferta();
        }
    }


    private JTextField campo1;
    private JButton sendButton;
    private String serverIP;

}