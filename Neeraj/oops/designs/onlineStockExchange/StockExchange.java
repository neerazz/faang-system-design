package onlineStockExchange;

public class StockExchange {
    public static final String name = "NeerajWood";
    OrderService orderService;
    WaltService waltService;
    StockService stockService;
    ExternalAPI externalAPI;

    private static StockExchange stockExchange = null;

    public static StockExchange getInstance() {
        if (stockExchange == null) {
            synchronized (StockExchange.class) {
                if (stockExchange == null) {
                    stockExchange = new StockExchange();
                }
            }
        }
        return stockExchange;
    }

    private StockExchange() {
        externalAPI = new ExternalAPI();
        stockService = new StockService(externalAPI);
        waltService = new WaltService();
        orderService = new OrderService(stockService, waltService);
    }
}
