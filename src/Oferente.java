import java.io.Serializable;

public class Oferente extends Usuario implements IOferente, Serializable {

    protected String ip;
    public Oferente(String ip) {
        this.ip=ip;
    }

    public Oferente() {
    }

    @Override
    public void unirSubasta(Subasta subasta) {

    }

    @Override
    public void ofrecerMonto() {

    }

    @Override
    public int getMonto() {
        return 0;
    }


    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public String getIp() {

        return this.ip;
    }



}
