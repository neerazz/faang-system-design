
public class EagerSingleton {

    //Initialized at launch
    private static final EagerSingleton instance = new EagerSingleton();

    //Private constructor to avoid new instance creation
    private EagerSingleton() {
    }

    //Getter to Return the instance
    public EagerSingleton gteInstance() {
        return instance;
    }

}
