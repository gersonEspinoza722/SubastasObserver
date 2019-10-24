public interface IObservable {
    void notifyAllOferentes();
    void addObserver (IObserver observer, int id);
    void removeObserver(IObserver observer, int id);
}
