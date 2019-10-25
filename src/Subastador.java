import java.io.Serializable;

public class Subastador extends Usuario implements ISubastador, Serializable {
    private Subasta subasta;
    private String ip;

    public Subastador(String ip) {
        this.ip=ip;
        //this.subasta = subasta;
    }



    @Override
    public void rechazarOferta() {

    }

    @Override
    public void cerrarSubasta() {

    }

    @Override
    public void cancelarSubasta() {

    }

    @Override
    public void enviarMensaje(Usuario usuario) {

    }

    @Override
    public void createAuction() {

    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public String getIp() {
        return ip;
    }

}
