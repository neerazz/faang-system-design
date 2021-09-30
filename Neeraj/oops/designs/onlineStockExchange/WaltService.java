package onlineStockExchange;

import java.util.HashMap;
import java.util.Map;

public class WaltService {

    Map<String, Double> balances = new HashMap<>();

    Double addBalance(String userId, Double bal) {
        balances.put(userId, getBalance(userId) + bal);
        return getBalance(userId);
    }

    Double reduceBalance(String userId, Double bal) {
        balances.put(userId, getBalance(userId) - bal);
        return getBalance(userId);
    }

    Double getBalance(String userId) {
        return balances.getOrDefault(userId, 0.0);
    }
}
