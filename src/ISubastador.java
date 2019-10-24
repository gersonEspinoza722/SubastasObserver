public interface ISubastador extends IObserver{
    void aceptarOferta(int monto, Usuario usuario);
    void cerrarSubasta();
    void cancelarSubasta();
    void enviarMensaje(Usuario usuario);
}
