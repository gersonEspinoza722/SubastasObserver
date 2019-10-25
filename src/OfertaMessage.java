import java.io.Serializable;

public class OfertaMessage implements IMessage, Serializable, IObserver{
    ISubasta subasta;
    int monto;
    String ip;

    public OfertaMessage(ISubasta subasta, int monto, String ip) {
        this.subasta = subasta;
        this.monto = monto;
        this.ip = ip;
    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public ISubasta getSubasta() {
        return this.subasta;
    }

    public void setSubasta(ISubasta subasta) {
        this.subasta = subasta;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
