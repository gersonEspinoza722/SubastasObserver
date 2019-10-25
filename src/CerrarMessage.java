import java.io.Serializable;
import java.util.ArrayList;

public class CerrarMessage implements ISubasta,IMessage, Serializable,IObserver {
    private Subasta subasta;

    public CerrarMessage(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public ISubasta getSubasta() {
        return this.subasta;
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
        return 0;
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
