public class Oferente extends Usuario implements IOferente{

    public Oferente(String username) {
        super(username);
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
    public String getIp() {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
