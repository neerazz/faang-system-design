package behavioral;

import java.util.*;
import java.io.*;

/**
 * @implNote <p>
 * 1. Broker is a call that Takes a stock and execute a stock. <br>
 * 2. You have an Order interface that takes care of placing the order, and you can have many implementation of that. <br>
 * a. BuyStock, that will only buy a stock. <br>
 * b. SellStock, this will only sell a stock. <br>
 * 3. This pattern helps broker to take an order and execute that. <br>
 * a. Broker and end user doesn't know what and how the execution will be done. <br>
 * b. All it cares is to execute the order. <br>
 * </p>
 */

public class CommandPattern {

    public static void main(String[] args) {
        Stock abcStock = new Stock();
//        For simplicity, hard coding the order amount.
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrders();
    }
}

class Stock {

    String name = "ABC";
    int quantity = 10;

    public void buy() {
        System.out.println("Stock    : [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock    : [ Name: " + name + ", Quantity:" + quantity + " ] sold ");
    }
}

interface Order {
    void execute();
}

class BuyStock implements Order {
    Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        System.out.println("BuyStock : Buying stock " + stock.name);
        stock.buy();
    }
}

class SellStock implements Order {
    Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        System.out.println("SellStock: Selling stock " + stock.name);
        stock.sell();
    }
}

class Broker {
    List<Order> orders = new ArrayList<>();

    void takeOrder(Order order) {
        orders.add(order);
    }

    void placeOrders() {
        System.out.println("Broker   : Placing Orders.");
        for (Order order : orders) {
            order.execute();
        }
    }
}