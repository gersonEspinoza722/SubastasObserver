import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Menu{
    public static void main(String[] args) {
    // TODO Auto-generated method stub

        MenuFrame mimarco=new MenuFrame();

    mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}



class MenuFrame extends JFrame{

    public MenuFrame(){

        setBounds(600,300,280,350);
        MenuPanel milamina=new MenuPanel();

        add(milamina);

        setVisible(true);


    }

}

class MenuPanel extends JPanel{
    private Usuario usuario;
    private ISubasta subasta;

    private static final int type = 0;

    public MenuPanel() {

        JLabel texto = new JLabel("MENU");
        add(texto);

        sendButton = new JButton("Log In a Subasta");
        SendOffer sendOfferEvent = new SendOffer();
        sendButton.addActionListener(sendOfferEvent);
        add(sendButton);

        createAuction = new JButton("Crear Subasta");
        CAuct cauct = new CAuct();
        createAuction.addActionListener(cauct);
        add(createAuction);

        auctionerView = new JButton("Verificar Subasta propia");
        Auct auct = new Auct();
        auctionerView.addActionListener(auct);
        add(auctionerView);

        oferenteView = new JButton("Ofertar en Subasta");
        Offer o = new Offer();
        oferenteView.addActionListener(o);
        add(oferenteView);






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




    private class SendOffer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OferenteLogInView logIn = new OferenteLogInView();
        }
    }
    private class CAuct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CreateAuctionView logIn = new CreateAuctionView();
        }
    }

    private class Auct implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AuctionerView logIn = new AuctionerView();
        }
    }

    private class Offer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ObjectAuctionView logIn = new ObjectAuctionView();
        }
    }


    private JTextField campo1;
    private JTextField campoSub;
    private JTextField respuesta;
    private JTextField ip;

    private JButton sendButton;
    private JButton createAuction;
    private JButton auctionerView;
    private JButton oferenteView;
}