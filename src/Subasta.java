import java.util.Date;

public class Subasta {
    private Date horaInicio;
    private Date horaFinal;
    private Producto producto;
    private int status;
    private int tope;

    public Subasta(Date horaInicio, Date horaFinal, Producto producto, int tope) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.producto = producto;
        this.status = Status.INICIADA;
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

    public int getStatus() {
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
