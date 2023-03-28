package Backend.stock;

import Backend.Enum.TransactionType;
import Backend.account.AccountService;
import Backend.account.SecurityAccount;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/4/2022 7:37 AM
 * @Description: Stock Service
 */
public class StockService implements StockServiceInterface {
    private StockDAO stockDAO;
    private AccountService accountService;
    public StockService(){
        stockDAO = new StockDAO();
        accountService = new AccountService();
    }

    public List<Stock> getAllStock() throws SQLException {
        return stockDAO.getAllStock();
    }

    public void buyStock(long stockId,long accountId, int buyAmount,double stockPrice){
        stockDAO.createNewStockRelationship(stockId,accountId,buyAmount,stockPrice);
    }

    public void sellStock(SecurityAccount account, int relationId, double buyingPrice, double currentPrice, int amount) throws SQLException {
        double totalSale = currentPrice * amount;
        account.setBalance(account.getBalance() + totalSale);
        accountService.updateAccountBalance(account,totalSale, TransactionType.SELL_STOCK.getTypeIndex());
        double profit = currentPrice * amount - buyingPrice * amount;
        double accountProfit = accountService.getAccountProfit(account.getId());
        accountService.updateAccountProfit(account.getId(),accountProfit + profit);
        stockDAO.removeStockRelationship(relationId);
    }

    public void updateStockPrice(Stock stock) throws SQLException {
        if(stockDAO.checkIfExistingStock(stock)){
            stockDAO.updateStockPrice(stock);
        }
        else{
            stockDAO.createNewStockListing(stock);
        }
    }

    public void getMyStockList(SecurityAccount account) throws SQLException {
        account.setOwnStack(stockDAO.getMyStockList(account.getId()));
        account.setRealizedProfit(stockDAO.getAccountProfit(account.getId()));
        double unProfit = 0;
        for (MyStock myStock:account.getOwnStack()){
            double buyingPrice = myStock.getBuyingPrice();
            int buyingAmount = myStock.getBuyingAmount();
            double price = myStock.getPrice();
            unProfit += (price - buyingPrice) * buyingAmount;
        }
        account.setUnrealizedProfit(unProfit);
    }


}
