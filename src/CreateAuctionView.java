import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class CreateAuctionView {
    public CreateAuctionView() {
        CreateAuctionFrame mimarco=new CreateAuctionFrame();

        //mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        CreateAuctionFrame mimarco=new CreateAuctionFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



class CreateAuctionFrame extends JFrame{

    public CreateAuctionFrame(){

        setBounds(600,300,280,350);
        CreateAuctionPanel milamina=new CreateAuctionPanel();

        add(milamina);

        setVisible(true);


    }

}

class CreateAuctionPanel extends JPanel implements ISubastador{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public CreateAuctionPanel(){

        JLabel texto=new JLabel("CREAR SUBASTA");
        add(texto);

        minutesField=new JTextField(20);
        add(minutesField);
        precioInicial=new JTextField(20);
        add(precioInicial);
        topeInicialField=new JTextField(20);
        add(topeInicialField);
        idSub=new JTextField(20);
        add(idSub);

        sendButton=new JButton("Enviar");
        CreateAuction createAuctionEvent = new CreateAuction();
        sendButton.addActionListener(createAuctionEvent);
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
        System.out.println("Notify");
        //Sacamos hora inicio
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //System.out.println(dtf.format(now));
/////////////////////////////////////////////////////////////////
        Producto prod = new Producto(Integer.valueOf(precioInicial.getText()),0);

        Subastador subastador = null;
        try {
            subastador = new Subastador(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        int minutes=Integer.valueOf(minutesField.getText());

        Subasta subastaNueva = new Subasta(now, now.plusMinutes(minutes),prod, Integer.valueOf(topeInicialField.getText()),Integer.valueOf(idSub.getText()),subastador);



        IMessage createAuctionMessage;

        createAuctionMessage = new CreateAuctionMessage(subastaNueva,subastador);

            try {

                Socket socket=new Socket("127.0.0.1",88);
                OutputStream streamToServer=socket.getOutputStream();
                ObjectOutputStream objectStreamToServer=new ObjectOutputStream(streamToServer);
                System.out.println("Va a escribir");
                objectStreamToServer.writeObject(createAuctionMessage);
                objectStreamToServer.close();

                socket.close();


            } catch (IOException ex) {
                ex.printStackTrace();

        }
    }

    @Override
    public int getType() {
        return 8;
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
        System.out.println("BOTON PRESSED");

        notifyObservable();


    }




    @Override
    public String getIp() {
        return null;
    }


    private class CreateAuction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createAuction();
        }
    }




    private JButton sendButton;

    //User data
    private JTextField minutesField;
    private JTextField precioInicial;
    private JTextField topeInicialField;
    private JTextField idSub;


    //Subasta data





}