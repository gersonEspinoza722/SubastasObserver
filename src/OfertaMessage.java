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
}
