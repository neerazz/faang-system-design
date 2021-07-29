package singleton;

public class ThreadSafeSingleton {

    //Static instance
    private static ThreadSafeSingleton instance;

    //Private constructor
    private ThreadSafeSingleton() {
    }

    //Serialized getter  - Expensive for every call.
    public synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

}
