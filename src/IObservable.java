public interface IObservable {
    void notifyAllOferentes(ISubasta subasta);
    void addObserver (IObserver observer, int id);
    void removeObserver(IObserver observer, int id);
}
