package singleton;

public class DoubleCheckThreadSafeSingleton {

    //Private instance
    private volatile DoubleCheckThreadSafeSingleton instance;

    //Private Constructor
    private DoubleCheckThreadSafeSingleton() {
    }

    //Synchronized Getter - Only for new instances
    public DoubleCheckThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
