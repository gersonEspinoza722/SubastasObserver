public interface IOferente extends IObserver {
    void unirSubasta(Subasta subasta);
    void ofrecerMonto();
    int getMonto();
}
