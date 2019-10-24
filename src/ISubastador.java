public interface ISubastador extends IObserver{
    void rechazarOferta(); //modify quitar parametros y borrar la ultima. desde pantalla subastador tenemos: id subasta
    void cerrarSubasta();
    void cancelarSubasta();
    void enviarMensaje(Usuario usuario);
    void createAuction();
}
