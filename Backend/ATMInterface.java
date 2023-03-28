package Backend;

import Backend.account.Account;
import Backend.account.SecurityAccount;
import Backend.currency.Currency;
import Backend.customer.Customer;
import Backend.loan.Collateral;
import Backend.loan.Loan;
import Backend.stock.Stock;
import Backend.transaction.TransactionHistory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:14 AM
 * @Description: TODO
 */
public interface ATMInterface {
    public Map<String,String> register(Customer customer) throws SQLException;
    public Map<String,Object> login(String id,String password) throws SQLException;
    public void createNewAccount(String ownerId,int type,double depositAmount) throws SQLException;
    public void deposit(Account account, double amount) throws SQLException;
    public void withdraw(Account account,double amount) throws SQLException;
    public void transaction(long senderId,double amount,long receiverId) throws SQLException;
    public Customer refreshCustomerData(Customer customer) throws SQLException;
    public void transfer(long sendId,long receiveId, double amount) throws SQLException;
    public boolean searchAccountById(long id) throws SQLException;
    public List<Stock> getStockList() throws SQLException;
    public void buyStock(Account account,Stock stock,int buyingAmount) throws SQLException;
    public List<TransactionHistory> getAccountTransaction(Long accountId) throws SQLException;
    public void getMyStockList(SecurityAccount account) throws SQLException;
    public void sellStock(SecurityAccount account, int stockID, double buyingPrice, double currentPrice, int amount) throws SQLException;
    public void sellCurrency(Currency currency, Account account) throws SQLException;
    public List<Currency> getAllCurrencyList() throws SQLException;
    public List<Currency> getAccountCurrencyList(long accountId) throws SQLException;
    public void buyCurrency(Currency currency, Account account) throws SQLException;
    public List<Collateral> getAllCollateral() throws SQLException;
    public void takeLoan(Customer customer,int collateralID,double collateralPrice) throws SQLException;
    public void returnLoan(Customer customer, int loanId,double collateralPrice) throws SQLException;
    public List<Loan> getCustomerLoan(String customerId) throws SQLException;
}
