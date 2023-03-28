package Backend.stock;

import Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/4/2022 7:37 AM
 * @Description: Stock DAO
 */
public class StockDAO {
    private DBUtil dbUtil;
    public StockDAO(){
        dbUtil = new DBUtil();
    }

    public List<Stock> getAccountOwnedStock(long accountId) throws SQLException {
        String sql = "select * from stockOwnRelationship where accountId = ?";
        ResultSet select = dbUtil.select(sql, new Object[]{accountId});
        List<Stock> stockList = new ArrayList<>();
        while (select.next()){

        }
        return  stockList;
    }

    public List<Stock> getAllStock() throws SQLException {
        String sql = "select * from stock";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{});
        List<Stock> stockList = new ArrayList<>();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            stockList.add(new Stock(id,name,price));
        }
        return stockList;
    }

    public void createNewStockRelationship(long stockId,long accountId, int buyAmount, double stockPrice){
        String sql = "insert into stockOwnRelationship(stockId,accountId,buyingAmount,buyingPrice) values(?,?,?,?)";
        Object[] para = new Object[]{stockId,accountId,buyAmount,stockPrice};
        dbUtil.update(sql,para);
    }

    public void createNewStockListing(Stock stock){
        String sql = "insert into stock(id,name,price) values(?,?,?)";
        Object[] para = new Object[]{stock.getId(), stock.getName(), stock.getPrice()};
        dbUtil.update(sql,para);
    }

    public void removeStockRelationship(int relationId){
        String sql = "delete from stockOwnRelationship where id = ? ";
        dbUtil.update(sql,new Object[]{relationId});
    }

    public void updateStockPrice(Stock stock){
        String sql = "update stock set price = ? where id = ?";
        Object[] para = new Object[]{stock.getPrice(),stock.getId()};
        dbUtil.update(sql,para);
    }

    public boolean checkIfExistingStock(Stock stock) throws SQLException {
        String sql = "select * from stock where id = ?";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{stock.getId()});
        if (!resultSet.isBeforeFirst() ) {
            return false;
        }
        return true;
    }

    public List<MyStock> getMyStockList(long accountId) throws SQLException {
        String sql = "select * from stockOwnRelationship where accountId = ?";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{accountId});
        List<MyStock> list = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int stockId = resultSet.getInt("stockId");
            int buyingAmount = resultSet.getInt("buyingAmount");
            double buyingPrice = resultSet.getDouble("buyingPrice");
            String sql1 = "select * from stock where id = ?";
            ResultSet select = dbUtil.select(sql1, new Object[]{stockId});
            String name ="";
            double currentPrice = 0;
            if (select.next()){
                name = select.getString("name");
                currentPrice = select.getDouble("price");
            }
            MyStock myStock = new MyStock(id,name,currentPrice,stockId,buyingPrice,buyingAmount);
            list.add(myStock);
        }
        return  list;
    }

    public double getAccountProfit(long accountId) throws SQLException {
        String sql = "select * from securityAccountProfit where accountId = ?";
        ResultSet select = dbUtil.select(sql, new Object[]{accountId});
        double profit = 0;
        if (select.next()){
            profit = select.getDouble("realizedProfit");
        }
        return profit;
    }
}
