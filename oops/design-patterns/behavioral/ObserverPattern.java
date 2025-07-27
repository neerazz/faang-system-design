import java.util.*;
import java.io.*;

/**
 * @implNote<p> 1. Assume you are an even organizer, who organizes different events. <br>
 * 2. You needs to get help from different type of workers who observers for events. So that they can work and get paid. Assume below are two type of workers: <br><p>
 * a. FinanceWorker (who deals with money in event)<br>
 * b. CateringWorker (Who deals with Food supply).<br>
 * 3. You want to build a software so that when there is an event: <br><p>
 * a. You say that there is an event, and all the workers gets alerted and start preparing for work. <br>
 * b. During the event there some change in date, you will again tell your software and that will take care of updating all the workers. <br>
 */

public class ObserverPattern {

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Worker financeWorker = new FinanceWorker();
        Worker cateringWorker = new CateringWorker();
        eventManager.addWorker(financeWorker);
        eventManager.addWorker(cateringWorker);

        Editor editor = new Editor(eventManager);
        editor.update("Got a new Event for Friday night");
        editor.update("Sorry, the Friday even is now moved to Saturday night");
    }
}

interface Worker {
    void update(String message);
}

class FinanceWorker implements Worker {

    @Override
    public void update(String message) {
        System.out.println("FinanceWorker : I got, " + message);
    }
}

class CateringWorker implements Worker {

    @Override
    public void update(String message) {
        System.out.println("CateringWorker: I got, " + message);
    }
}

class EventManager {
    List<Worker> workers = new ArrayList<>();

    void addWorker(Worker worker) {
        workers.add(worker);
    }

    void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    void notifyAll(String message) {
        System.out.println("*************************************************************");
        System.out.println("EventManager  : Sending to all.");
        for (Worker worker : workers) {
            worker.update(message);
        }
        System.out.println("*************************************************************");
    }
}

class Editor {
    EventManager eventManager;

    public Editor(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void update(String message) {
        System.out.println("Editor        : " + message);
        eventManager.notifyAll(message);
    }
}