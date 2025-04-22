package observer;

public interface UserObservable {
    void addObserver(UserObserver o);
    void removeObserver(UserObserver o);
    void notifyObservers();
}
