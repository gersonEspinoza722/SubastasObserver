import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Subasta implements ISubasta, Serializable {
    int id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private Producto producto;
    private int status;
    private int tope;
    private ArrayList<IMessage> ofertas;
    private ArrayList<IOferente> oferentes;
    private ISubastador subastador;

    public Subasta(LocalDateTime horaInicio, LocalDateTime horaFinal, Producto producto, int tope, int id, ISubastador sub) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.producto = producto;
        this.status = Status.INICIADA;
        this.tope = tope;
        this.id=id;
        this.subastador=sub;
        oferentes = new ArrayList<>();
        ofertas = new ArrayList<>();

    }

    public Subasta() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<IOferente> getOferentes() {
        return oferentes;
    }

    public void setOferentes(ArrayList<IOferente> oferentes) {
        this.oferentes = oferentes;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public int getTope() {
        return tope;
    }

    @Override
    public ArrayList<IMessage> getOfertas() {
        return ofertas;
    }

    @Override
    public void addOferta(IMessage ofertaNotify) {
        this.ofertas.add(ofertaNotify);
    }




    public void setOfertas(ArrayList<IMessage> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public ISubastador getSubastador() {
        return subastador;
    }

    public void setSubastador(ISubastador subastador) {
        this.subastador = subastador;
    }

    @Override
    public void addOferente(IOferente oferente) {
oferentes.add(oferente);
    }

    @Override
    public void removeOferente(IOferente oferente) {

    }

    public void setTope(int tope) {
        this.tope = tope;
    }
}
