public interface ISubastador {
    void aceptarOferta(int monto, Usuario usuario);
    void cerrarSubasta();
    void cancelarSubasta();
    void enviarMensaje(Usuario usuario);
}
