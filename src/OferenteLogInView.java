import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class OferenteLogInView{


        public static void main(String[] args) {
            // TODO Auto-generated method stub

            LogInFrame mimarco=new LogInFrame();

            mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}



class LogInFrame extends JFrame{

    public LogInFrame(){

        setBounds(600,300,280,350);
        LogInPanel milamina=new LogInPanel();

        add(milamina);

        setVisible(true);


    }

}

class LogInPanel extends JPanel implements IOferente, IMessage {
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 1;

    public LogInPanel() {

        JLabel texto = new JLabel("AGREGAR OFERENTE A SUBASTA");
        add(texto);

        idSubastaField = new JTextField(5);
        add(idSubastaField);



        sendButton = new JButton("Agregar");
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

    public ISubasta getSubasta() {
        return subasta;
    }

    public void setSubasta(ISubasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void notifyObservable() {

        IMessage logInMessage;

        logInMessage=null;

        try {
            logInMessage = new LogInMessage(Integer.valueOf(idSubastaField.getText()), InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
                Socket socket = new Socket("127.0.0.1", 88);
                OutputStream streamToServer = socket.getOutputStream();
                ObjectOutputStream objectStreamToServer = new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(logInMessage);
                objectStreamToServer.close();
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
        return 0;
    }

    @Override
    public String getIp() {
        return this.usuario.ip;
    }


    private class SendOffer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            notifyObservable();
        }
    }



    private JTextField idSubastaField;
    private JButton sendButton;
}