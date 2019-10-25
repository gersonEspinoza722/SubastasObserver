public class OfertaMessage implements IMessage {
    ISubasta subasta;
    int monto;
    String ip;

    public OfertaMessage(ISubasta subasta, int monto, String ip) {
        this.subasta = subasta;
        this.monto = monto;
        this.ip = ip;
    }

    @Override
    public Class getType() {
        return this.getType();
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
