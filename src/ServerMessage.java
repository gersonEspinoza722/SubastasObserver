public class ServerMessage implements IMessage {
    ISubasta subasta;

    public ServerMessage(Subasta subasta) {
        this.subasta = subasta;
    }

    public ISubasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public int getType() {
        return 3;
    }
}
