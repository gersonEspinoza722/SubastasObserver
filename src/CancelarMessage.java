import java.io.Serializable;

public class CancelarMessage implements IMessage, IObserver, Serializable {
    private Subasta subasta;

    public CancelarMessage(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public ISubasta getSubasta() {
        return subasta;
    }
}
