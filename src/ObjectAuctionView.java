import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ObjectAuctionView implements IObserver{

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AuctionFrame mimarco=new AuctionFrame();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void notifyObservable() {
        //implementar
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

class AuctionPanel extends JPanel{

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


    private class SendOffer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Pasa la vara
            if(!campo1.getText().isEmpty()){
                int value = Integer.valueOf(campo1.getText());

                try {
                    Socket socket=new Socket(serverIP,88);
                    DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
                    streamToServer.writeUTF(String.valueOf(value));

                    streamToServer.close();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }




        }
    }



    private JTextField campo1;

    private JButton sendButton;

    private String serverIP;

}