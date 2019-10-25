import java.io.Serializable;
public class LogInMessage implements IOferente,IMessage, Serializable,IObserver{

    Subasta subasta;
    int type;

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Oferente getUsuario() {
        return (Oferente) usuario;
    }

    public void setUsuario(Oferente usuario) {
        this.usuario = usuario;
    }

    IOferente usuario;

    public void setType(int type) {
        this.type = type;
    }



    public LogInMessage(int idSubasta, String ip) {
        this.subasta = new Subasta();
        this.usuario = new Oferente(ip);

        type =1;
        this.subasta.setId(idSubasta);

    }

    @Override
    public void notifyObservable() {

    }

    @Override
    public int getType() {
        return type;
    }


    @Override
    public ISubasta getSubasta() {
        return subasta;
    }




    @Override
    public void unirSubasta(Subasta subasta) {

    }

    @Override
    public void ofrecerMonto() {

    }

    @Override
    public int getMonto() {
        return subasta.getTope();
    }

    @Override
    public String getIp() {
        System.out.println("pide ip aqui 1");
        return usuario.getIp();
    }

}
