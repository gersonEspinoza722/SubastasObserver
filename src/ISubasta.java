import java.util.ArrayList;

public interface ISubasta{
    int getTope();
    void setTope(int tope);
    void addOferente(IOferente oferente);
    void removeOferente(IOferente oferente);
    int getId();
    ArrayList<IMessage> getOfertas();
    void addOferta(IMessage ofertaNotify);
    ArrayList<IOferente> getOferentes();
    ISubastador getSubastador();
}
