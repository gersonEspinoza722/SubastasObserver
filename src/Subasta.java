import java.util.Date;

public class Subasta {
    private Date horaInicio;
    private Date horaFinal;
    private Producto producto;
    private Character status; //clase? enum?
    private int tope;

    public Subasta(Date horaInicio, Date horaFinal, Producto producto, Character status, int tope) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.producto = producto;
        this.status = status;
        this.tope = tope;
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

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }
}
