package coding.currency;

import Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/10/2022 5:19 PM
 * @Description: TODO
 */
public class CurrencyDAO {
    private DBUtil db;

    public CurrencyDAO(){
        this.db = new DBUtil();
    }

    public void saveNewCurrency(Currency currency,long accountID){
        String sql = "insert into savingAccountCurrency(accountId,amount,currencyId) VALUES(?,?,?)";
        Object[] para = new Object[]{accountID,currency.getAmount(),currency.getId()};
        db.update(sql,para);
    }

    public void updateCurrencyAmount(Currency currency,long accountID){
        String sql = "update savingAccountCurrency set amount = ? where accountId = ? and currencyId = ?";
        Object[] para = new Object[]{currency.getAmount(),accountID,currency.getId()};
        db.update(sql,para);
    }

    public int getCurrencyAmount(long accountId, int currencyId) throws SQLException {
        String sql = "select * from savingAccountCurrency where accountId = ? and currencyId = ?";
        Object[] para = new Object[]{accountId,currencyId};
        ResultSet resultSet = db.select(sql, para);
        int ownAmount = -1;
        if (resultSet.next()){
            ownAmount = resultSet.getInt("amount");
        }
        return ownAmount;
    }

    public void removeCurrency(int currencyId,long accountID){
        String sql = "delete from savingAccountCurrency where accountId = ? and currencyId = ? ";
        Object[] para = new Object[]{accountID,currencyId};
        db.update(sql,para);
    }

    public List<Currency> getAccountCurrencyList(long accountId) throws SQLException {
        String sql = "select * from savingAccountCurrency where accountId = ?";
        ResultSet resultSet = db.select(sql, new Object[]{accountId});
        List<Currency> currencyList = new ArrayList<>();
        while (resultSet.next()){
            int currencyId = resultSet.getInt("currencyId");
            int amount = resultSet.getInt("amount");
            sql = "select * from currency where id = ?";
            ResultSet currencyDB = db.select(sql, new Object[]{currencyId});
            String name = "";
            double rate = 0;
            if (currencyDB.next()){
                name = currencyDB.getString("name");
                rate = currencyDB.getDouble("rate");
            }
            currencyList.add(new Currency(currencyId,name,rate,amount));
        }
        return currencyList;
    }

    public List<Currency> getAllCurrencyList() throws SQLException {
        String sql = "select * from currency";
        ResultSet resultSet = db.select(sql, new Object[]{});
        List<Currency> currencyList = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double rate = resultSet.getDouble("rate");
            currencyList.add(new Currency(id,name,rate,0));
        }
        return currencyList;
    }


}
