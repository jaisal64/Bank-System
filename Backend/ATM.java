package Backend;

import Utils.StaticData;
import Backend.Enum.TransactionType;
import Backend.account.Account;
import Backend.account.AccountService;
import Backend.account.SecurityAccount;
import Backend.currency.Currency;
import Backend.currency.CurrencyService;
import Backend.customer.Customer;
import Backend.customer.CustomerService;
import Backend.loan.Collateral;
import Backend.loan.Loan;
import Backend.loan.LoanService;
import Backend.stock.Stock;
import Backend.stock.StockService;
import Backend.transaction.TransactionHistory;
import Backend.transaction.TransactionService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:38 PM
 * @Description: ATM
 */
public class ATM implements ATMInterface{
    private CustomerService customerService;
    private AccountService accountService;

    private StockService stockService;

    private TransactionService transactionService;

    private CurrencyService currencyService;

    private LoanService loanService;

    public ATM(){
        customerService = new CustomerService();
        this.accountService = new AccountService();
        this.stockService = new StockService();
        this.transactionService = new TransactionService();
        this.currencyService = new CurrencyService();
        this.loanService = new LoanService();
    }

    public Map<String,String> register(Customer customer) throws SQLException {
        HashMap<String, String> result = new HashMap<>();
        Customer customerById = customerService.findCustomerById(customer.getId());
        if (customerById!=null){
            result.put(StaticData.MESSAGE, StaticData.CREATE_SUCCESSFUL);
            return result;
        }else{
            Boolean register = customerService.register(customer);
            if (!register){
                result.put(StaticData.MESSAGE,StaticData.DATABASE_FAIL);
                return result;
            }
        }
        return result;
    }

    public Map<String,Object> login(String id,String password) throws SQLException {
        HashMap<String, Object> result = new HashMap<>();
        Customer login = customerService.getCustomerData(id, password);
        if (login!=null){
            result.put(StaticData.CUSTOMER,login);
        }else{
            result.put(StaticData.MESSAGE,StaticData.LOGIN_FAIL);
        }
        return result;
    }

    public void createNewAccount(String ownerId,int type,double depositAmount) throws SQLException {
        accountService.createNewAccount(ownerId, type,depositAmount);
    }


    public void deposit(Account account,double amount) throws SQLException {
        account.setBalance(account.getBalance()+amount);
        accountService.updateAccountBalance(account,amount, TransactionType.DEPOSIT.getTypeIndex());
    }

    public void withdraw(Account account,double amount) throws SQLException {
        account.setBalance(account.getBalance()-amount-1);
        accountService.updateAccountBalance(account,amount,TransactionType.WITHDRAW.getTypeIndex());
        accountService.paySystemFee(account.getId());
    }

    public void transaction(long senderId,double amount,long receiverId) throws SQLException {
        accountService.transaction(senderId,amount,receiverId);
    }

    public Customer refreshCustomerData(Customer customer) throws SQLException {
        return customerService.getCustomerData(customer.getId(), customer.getPassword());
    }

    public void transfer(long sendId,long receiveId, double amount) throws SQLException {
        accountService.transfer(sendId,receiveId,amount);
    }

    public boolean searchAccountById(long id) throws SQLException {
        return accountService.searchAccountById(id);
    }

    public List<Stock> getStockList() throws SQLException {
        return stockService.getAllStock();
    }

    public void buyStock(Account account,Stock stock,int buyingAmount) throws SQLException {
        stockService.buyStock(stock.getId(),account.getId(),buyingAmount,stock.getPrice());
        double totalPrice = buyingAmount * stock.getPrice();
        account.setBalance(account.getBalance()-totalPrice);
        accountService.updateAccountBalance(account,totalPrice,TransactionType.BUY_STOCK.getTypeIndex());
    }

    public List<TransactionHistory> getAccountTransaction(Long accountId) throws SQLException {
        return transactionService.getAccountTransaction(accountId);
    }

    public void getMyStockList(SecurityAccount account) throws SQLException {
        stockService.getMyStockList(account);
    }

    public void sellStock(SecurityAccount account, int stockID, double buyingPrice, double currentPrice, int amount) throws SQLException {
        stockService.sellStock(account,stockID,buyingPrice,currentPrice,amount);
    }

    public void sellCurrency(Currency currency, Account account) throws SQLException {
        currencyService.sellCurrency(currency,account);
    }

    public List<Currency> getAllCurrencyList() throws SQLException {
        return currencyService.getAllCurrencyList();
    }

    public List<Currency> getAccountCurrencyList(long accountId) throws SQLException {
        return currencyService.getAccountCurrencyList(accountId);
    }

    public void buyCurrency(Currency currency, Account account) throws SQLException {
        currencyService.buyCurrency(currency,account);
    }

    public Map<String,String> updateCustomerPassword(String id,String newPassword){
        HashMap<String, String> result = new HashMap<>();
        Boolean updateResult = customerService.updateCustomerPassword(id, newPassword);
        if (!updateResult){
            result.put(StaticData.MESSAGE,StaticData.DATABASE_FAIL);
            return result;
        }
        return result;
    }

    public Map<String,String> updateCustomerPhoneNum(String id,String newPhoneNum){
        HashMap<String, String> result = new HashMap<>();
        Boolean updateResult = customerService.updateCustomerPhoneNum(id, newPhoneNum);
        if (!updateResult){
            result.put(StaticData.MESSAGE,StaticData.DATABASE_FAIL);
            return result;
        }
        return result;
    }

    public List<Collateral> getAllCollateral() throws SQLException {
        return loanService.getAllCollateral();
    }

    public void takeLoan(Customer customer,int collateralID,double collateralPrice) throws SQLException {
        loanService.takeLoan(customer,collateralID,collateralPrice);
    }

    public void returnLoan(Customer customer, int loanId,double collateralPrice) throws SQLException {
        loanService.returnLoan(customer,loanId,collateralPrice);
    }

    public List<Loan> getCustomerLoan(String customerId) throws SQLException {
        return loanService.getCustomerLoan(customerId);
    }

}
