package Backend.account;

import Utils.DBUtil;
import Backend.Enum.AccountType;
import Backend.customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 5:46 PM
 * @Description: Account DAO
 */
public class AccountDAO {

    private DBUtil db;

    public AccountDAO(){
        this.db = new DBUtil();
    }

    public void searchAccountByOwnerId(Customer customer) throws SQLException {
        Object[] para = new Object[]{customer.getId()};
        String sql = "select * from account where ownerId = ?";
        ResultSet resultSet = db.select(sql, para);
        while (resultSet.next()){
            int type = resultSet.getInt("type");
            long id = resultSet.getLong("id");
            double balance = resultSet.getDouble("balance");
            if (type==AccountType.Security_Type.getTypeIndex()){
                customer.setSecurityAccount(new SecurityAccount(id,balance,new ArrayList<>()));
            }else if (type==AccountType.Checking_Type.getTypeIndex()){
                customer.setCheckingAccount(new CheckingAccount(id,balance,new ArrayList<>()));
            }else if (type==AccountType.Saving_Type.getTypeIndex()){
                customer.setSavingAccount(new SavingAccount(id,balance,new ArrayList<>()));
            }
        }
    }

    public void updateBalance(long accountId, double balance){
        Object[] para = new Object[]{balance,accountId};
        String sql = "update account set balance = ? where id = ?";
        db.update(sql, para);
    }


    public Boolean searchExistAccountById(Long id) throws SQLException {
        Object[] para = new Object[]{id};
        String sql = "select * from account where id = ?";
        ResultSet resultSet = db.select(sql, para);
        while(resultSet.next()){
            return true;
        }
        return false;
    }

    public long createNewAccount(Long id, String ownerId,int type,double depositAmount) throws SQLException {
        Object[] para = new Object[]{id,depositAmount,type,ownerId};
        String sql = "insert into account(id,balance,type,ownerId) values(?,?,?,?)";
        db.update(sql, para);
        Long accountId = getAccountByIdType(ownerId, type).getId();
        if (type==AccountType.Security_Type.getTypeIndex()){
            sql = "insert into securityAccountProfit(accountId,realizedProfit) values(?,?)";
            db.update(sql, new Object[]{accountId,0});
        }
        return accountId;
    }

    public void updateAccountProfit(long accountId, double profit){
        String sql = "update securityAccountProfit set realizedProfit = ? where accountId = ?";
        db.update(sql, new Object[]{profit,accountId});
    }

    public Account getAccountByIdType(String ownerId,int accountType) throws SQLException {
        Object[] para = new Object[]{ownerId,accountType};
        String sql = "select * from account where ownerId = ? and type = ?";
        ResultSet result = db.select(sql, para);
        Long id = 0L;
        Double balance = 0.0;
        while (result.next()) {
            id = result.getLong("id");
            balance = result.getDouble("balance");
        }
        if (accountType==AccountType.Saving_Type.getTypeIndex()){
            return new SavingAccount(id,balance,new ArrayList<>());
        }else if (accountType==AccountType.Checking_Type.getTypeIndex()){
            return new CheckingAccount(id,balance,new ArrayList<>());
        }else if (accountType==AccountType.Security_Type.getTypeIndex()){
            return new SecurityAccount(id,balance,new ArrayList<>());
        }
        return null;
    }

    public double getBalanceById(long accountId) throws SQLException {
        Object[] para = new Object[]{accountId};
        String sql = "select balance from account where id = ?";
        ResultSet resultSet = db.select(sql, para);
        if (resultSet.next()){
            return resultSet.getDouble("balance");
        }
        return 0.0;
    }

    public void transfer(long sendId,long receiveId,double amount) throws SQLException {
        double senderBalance = getBalanceById(sendId) - amount;
        double receiverBalance = getBalanceById(receiveId) + amount;
        String sql = "update account set balance = ? where id = ?";
        db.update(sql,new Object[]{senderBalance,sendId});
        db.update(sql,new Object[]{receiverBalance,receiveId});
    }

    public double getAccountProfit(long accountID) throws SQLException {
        String sql = "select * from securityAccountProfit where accountId = ?";
        ResultSet resultSet = db.select(sql, new Object[]{accountID});
        double realizedProfit = 0;
        if (resultSet.next()){
            realizedProfit = resultSet.getDouble("realizedProfit");
        }
        return realizedProfit;
    }




}
