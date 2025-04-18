package observer;

public interface WorkoutObservable {
    void addObserver(WorkoutObserver o);
    void removeObserver(WorkoutObserver o);
    void notifyObservers();
}
