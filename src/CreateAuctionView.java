import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateAuctionView {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AuctionFrame mimarco=new AuctionFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



class CreateAuctionFrame extends JFrame{

    public CreateAuctionFrame(){

        setBounds(600,300,280,350);
        AuctionPanel milamina=new AuctionPanel();

        add(milamina);

        setVisible(true);


    }

}

class CreateAuctionPanel extends JPanel implements ISubastador{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public CreateAuctionPanel(){

        JLabel texto=new JLabel("CLIENTE");
        add(texto);

        ipField=new JTextField(20);
        add(ipField);
        sendButton=new JButton("Enviar");
        CreateAution createAutionEvent = new CreateAution();
        sendButton.addActionListener(createAutionEvent);
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

            try {
                Socket socket=new Socket("127.0.0.1",88);
                // DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);

                objectStreamToServer.writeObject(subasta);

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();

        }
    }


    @Override
    public void rechazarOferta() {

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
        String ip = ipField.getText();

        //Usuario userSubastador = new User()

        //Producto productoSubastado = new Producto()

        //subasta = new Subasta();

        notifyObservable();


    }


    private class CreateAution implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createAuction();
        }
    }




    private JButton sendButton;

    //User data
    private JTextField ipField;
    //Subasta data
    private JTextField status;




}