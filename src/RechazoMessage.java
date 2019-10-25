import java.io.Serializable;

public class RechazoMessage implements IMessage, Serializable, IObserver{
    Subasta subasta;
    int type;

    public RechazoMessage(Subasta subasta) {
        this.subasta = subasta;
        this.type=3;
    }

    @Override
    public void notifyObservable() {

    }


    @Override
    public int getType() {
        return this.type;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }
}
