public class RechazoMessage implements IMessage{
    Subasta subasta;

    public RechazoMessage(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public Class getType() {
        return this.getType();
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }
}
