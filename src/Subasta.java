import java.util.ArrayList;
import java.util.Date;

public class Subasta implements ISubasta{
    int id;
    private Date horaInicio;
    private Date horaFinal;
    private Producto producto;
    private int status;
    private int tope;
    private ArrayList<IOferente> oferentes;
    private ISubastador subastador;

    public Subasta(Date horaInicio, Date horaFinal, Producto producto, int tope) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.producto = producto;
        this.status = Status.INICIADA;
        this.tope = tope;
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

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
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
    public void addOferente(IOferente oferente) {

    }

    @Override
    public void removeOferente(IOferente oferente) {

    }

    public void setTope(int tope) {
        this.tope = tope;
    }
}
