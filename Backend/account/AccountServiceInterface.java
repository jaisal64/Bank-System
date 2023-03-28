package Backend.account;

import Backend.customer.Customer;

import java.sql.SQLException;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:02 AM
 * @Description: TODO
 */
public interface AccountServiceInterface {
    public boolean searchAccountById(long id) throws SQLException;
    public void getCustomerAccountInfo(Customer customer) throws SQLException;
    public void createNewAccount(String ownerId,int type,double depositAmount) throws SQLException;
    public void updateAccountBalance(Account account,Double amount,int type) throws SQLException;
    public void paySystemFee(long accountId) throws SQLException;
    public void transaction(long sendId,Double amount,long receiveId) throws SQLException;
    public void transfer(long sendId,long receiveId,double amount)throws SQLException;
    public void updateAccountProfit(long accountId, double profit);
    public double getAccountProfit(long accountID) throws SQLException;
}
