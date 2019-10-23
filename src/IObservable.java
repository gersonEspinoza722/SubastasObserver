public interface IObservable {
    void notifyAllOferentes();
    void addObserver (IObserver observer);
    void removeObserver(IObserver observer);
}
