package onlineStockExchange;

import java.util.HashMap;
import java.util.Map;

public class StockService {
    ExternalAPI externalAPI;

    Map<String, Stock> inventory = new HashMap<>();

    public StockService(ExternalAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    public boolean buyOrder(Stock stock, Double quantity) {
        System.out.println("Making 3rd Party API call to Sell stock");
        return externalAPI.buyOrder(stock, quantity);
    }

    public boolean sellOrder(Stock stock, Double quantity) {
        System.out.println("Making 3rd Party API call to Sell stock");
        return externalAPI.sellOrder(stock, quantity);
    }
}

class Stock {
    String shortName;
    String description;
    Double price;
}

class StockBalance {
    Stock stock;
    Double quantity;

    public StockBalance(Stock stock, Double quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public void add(Double quantity) {
        this.quantity += quantity;
    }

    public void reduce(Double quantity) {
        this.quantity -= quantity;
    }
}