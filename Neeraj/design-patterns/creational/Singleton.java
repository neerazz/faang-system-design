package creational;

import java.util.*;
import java.io.*;

/**
 * Created on:  Feb 16, 2021
 * Questions:
 */

public class Singleton {

    public static void main(String[] args) {

    }
}

/**
 * @implNote This is an eager implementation of a singleton class.
 */
class SingleTon1 {
    private SingleTon1 instance = new SingleTon1();

    private SingleTon1() {

    }

    public SingleTon1 getInstance() {
        return instance;
    }
}

/**
 * @implNote This is a lazy implementation. Instance of the object will not be created till it is required.
 */
class SingleTon2 {
    private SingleTon2 instance = null;

    private SingleTon2() {

    }

    public SingleTon2 getInstance() {
        if (instance == null) instance = new SingleTon2();
        return instance;
    }
}

/**
 * @implNote This is a thread safe and a lazy implementation.
 */
class SingleTon3 {
    private SingleTon3 instance = null;

    private SingleTon3() {

    }

    public SingleTon3 getInstance() {
        if (instance == null) {
            synchronized (SingleTon3.class) {
                if (instance == null) instance = new SingleTon3();
            }
        }
        return instance;
    }
}