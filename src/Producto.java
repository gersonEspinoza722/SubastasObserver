public class Producto {
    private int precioInicial;
    private int precioFinal;

    public Producto(int precioInicial, int precioFinal) {
        this.precioInicial = precioInicial;
        this.precioFinal = precioFinal;
    }

    public int getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(int precioInicial) {
        this.precioInicial = precioInicial;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }
}
