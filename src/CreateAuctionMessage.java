import java.io.Serializable;
import java.util.ArrayList;

public class CreateAuctionMessage implements ISubasta,IMessage, Serializable,IObserver {
    private Subasta subasta;
    private Usuario subastador;

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public void setSubastador(Usuario subastador) {
        this.subastador = subastador;
    }

    public CreateAuctionMessage(Subasta subasta, Subastador subastador) {
        this.subasta = subasta;
        this.subastador = subastador;
    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public ISubasta getSubasta() {
        return subasta;
    }

    @Override
    public int getTope() {
        return 0;
    }

    @Override
    public void setTope(int tope) {

    }

    @Override
    public void addOferente(IOferente oferente) {

    }

    @Override
    public void removeOferente(IOferente oferente) {

    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public ArrayList<IMessage> getOfertas() {
        return null;
    }

    @Override
    public void addOferta(IMessage ofertaNotify) {

    }

    @Override
    public ArrayList<IOferente> getOferentes() {
        return null;
    }

    @Override
    public ISubastador getSubastador() {
        return null;
    }
}
