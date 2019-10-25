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
        AuctionerPanel milamina=new AuctionerPanel();

        add(milamina);

        setVisible(true);


    }

}

class AuctionerPanel extends JPanel implements ISubastador, Runnable{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public AuctionerPanel(){

        JLabel texto=new JLabel("SUBASTADOR");
        add(texto);

        campo1=new JTextField(20);
        add(campo1);
        respuesta=new JTextField(50);
        add(respuesta);
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
            ServerSocket server = new ServerSocket(9050);

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
                //campo1.setText(Integer.toString(inputO.getTope()));
                //notifyAllOferentes();

                streamFromClient.close();
                respuesta.setText("TOPE DE SU SUBASTA: "+inputO.getTope()+"(IDENTIFICADOR +"+inputO.getId()+")");

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
        IMessage rechazoMessage;

        Subasta sub = new Subasta();
        sub.setId(1);

        rechazoMessage = new RechazoMessage(sub);

            try {
                Socket socket=new Socket(serverIP,88);
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(rechazoMessage);

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }

    @Override
    public int getType() {return 3;
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

    @Override
    public String getIp() {
        return this.usuario.ip;
    }


    private class SendRechazo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rechazarOferta();
        }
    }


    private JTextField campo1;
    private JButton sendButton;
    private JTextField respuesta;
    private String serverIP;

}