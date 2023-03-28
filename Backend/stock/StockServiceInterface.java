package Backend.stock;

import Backend.account.SecurityAccount;

import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:05 AM
 * @Description: TODO
 */
public interface StockServiceInterface {
    public void buyStock(long stockId,long accountId, int buyAmount,double stockPrice);
    public void sellStock(SecurityAccount account, int relationId, double buyingPrice, double currentPrice, int amount) throws SQLException;
    public void updateStockPrice(Stock stock) throws SQLException;
    public void getMyStockList(SecurityAccount account) throws SQLException;
}
