import java.io.Serializable;
import java.util.ArrayList;

public class CerrarMessage implements IMessage, Serializable,IObserver {
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




}
