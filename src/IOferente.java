public interface IOferente extends IObserver {
    void unirSubasta(Subasta subasta); //se hace desde IObservable
    void ofrecerMonto();
    int getMonto();
    String getIp();
}
