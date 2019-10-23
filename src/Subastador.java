public class Subastador extends Usuario implements ISubastador{
    private Subasta subasta;

    public Subastador(String username, Subasta subasta) {
        super(username);
        this.subasta = subasta;
    }

    @Override
    public void aceptarOferta(int monto, Usuario usuario) {

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
}
