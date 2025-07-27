package onlineStockExchange;

public class OrderService {
    StockService stockService;
    WaltService waltService;

    public OrderService(StockService stockService, WaltService waltService) {
        this.stockService = stockService;
        this.waltService = waltService;
    }

    public boolean buyOrder(Order order) {
        String userId = order.userId;
        Double balance = waltService.getBalance(userId);
        if(balance >= order.amount){
            waltService.reduceBalance(order.userId, order.amount);
            boolean orderStatus = stockService.buyOrder(order.stock, order.quantity);
            if(orderStatus){
                order.status = Status.SUCCESS;
            }else{
                waltService.addBalance(order.userId, order.amount);
                order.status = Status.FAILED;
            }
            return orderStatus;
        }
        System.out.println("YOu don't have sufficient Balance");
        return false;
    }

    public boolean sellOrder(Order order) {
        boolean orderStatus = stockService.sellOrder(order.stock, order.quantity);
        if(orderStatus){
            waltService.addBalance(order.userId,order.amount);
            order.status = Status.SUCCESS;
        }else{
            order.status = Status.FAILED;
        }
        return orderStatus;
    }

}

abstract class Order {
    Stock stock;
    Double quantity;
    String userId;
    Double amount;
    OrderService orderService;
    Status status;

    public Order(Stock stock, Double quantity, String userId, OrderService orderService) {
        this.stock = stock;
        this.quantity = quantity;
        this.userId = userId;
        amount = quantity * stock.price;
        this.orderService = orderService;
    }

    abstract boolean placeOrder(Order order);
}

class SellOrder extends Order {

    public SellOrder(Stock stock, Double quantity, String userId, OrderService orderService) {
        super(stock, quantity, userId, orderService);
    }

    @Override
    boolean placeOrder(Order order) {
        return orderService.sellOrder(order);
    }
}

class BuyOrder extends Order {

    public BuyOrder(Stock stock, Double quantity, String userId, OrderService orderService) {
        super(stock, quantity, userId, orderService);
    }

    @Override
    boolean placeOrder(Order order) {
        return orderService.buyOrder(order);
    }
}
enum Status{
    SUCCESS, FAILED
}