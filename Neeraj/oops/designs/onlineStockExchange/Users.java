package onlineStockExchange;

import java.util.*;

public class Users {
    Map<String, Stock> watchList;
    List<Order> orders;
    String userId;
    Map<String, StockBalance> stocks;
    OrderService orderService;
    WaltService waltService;

    public Users(OrderService orderService, WaltService waltService) {
        this.orderService = orderService;
        this.waltService = waltService;
        this.userId = "User" + new Random().nextInt();
        orders = new ArrayList<>();
        stocks = new HashMap<>();
        watchList = new HashMap<>();
    }

    void addWatchList(Stock stock) {
        watchList.put(stock.shortName, stock);
    }

    void removeWatchList(String shortName) {
        if (watchList.containsKey(shortName)) {
            watchList.remove(shortName);
        } else {
            System.out.println("The stock " + shortName + " not present in your watch list.");
        }
    }

    List<Stock> getWatchList() {
        return new ArrayList<>(watchList.values());
    }

    boolean sellStock(Stock stock, Double quantity) {
        if (!stocks.containsKey(stock.shortName)) {
            System.out.println("You Don't Own the stock " + stock.shortName);
            return false;
        }
        Order order = new SellOrder(stock, quantity, userId, orderService);
        if (order.placeOrder(order)) {
            orders.add(order);
            stocks.get(stock.shortName).reduce(quantity);
            return true;
        }
        return false;
    }

    Double addBalance(Double amount){
        return waltService.addBalance(userId, amount);
    }

    boolean buyStock(Stock stock, Double quantity) {
        Order order = new BuyOrder(stock, quantity, userId, orderService);
        if (order.placeOrder(order)) {
            orders.add(order);
            if (stocks.containsKey(stock.shortName)) {
                stocks.get(stock.shortName).add(quantity);
            } else {
                stocks.put(stock.shortName, new StockBalance(stock, quantity));
            }
        }
        return false;
    }
}