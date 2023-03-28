package coding.account;

import Utils.CommonUtil;
import coding.Enum.TransactionType;
import coding.customer.Customer;
import coding.transaction.TransactionHistory;
import coding.transaction.TransactionService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 5:46 PM
 * @Description: TODO
 */
public class AccountService {
    private AccountDAO accountDAO;
    private TransactionService transactionService;

    public AccountService(){
        this.accountDAO = new AccountDAO();
        this.transactionService = new TransactionService();
    }

    public boolean searchAccountById(long id) throws SQLException {
        return accountDAO.searchExistAccountById(id);
    }

    public void getCustomerAccountInfo(Customer customer) throws SQLException {
        accountDAO.searchAccountByOwnerId(customer);
    }

    public void createNewAccount(String ownerId,int type,double depositAmount) throws SQLException {
        Long id = 0L;
        Boolean existAccountById = true;
        while (existAccountById){
           id =  CommonUtil.getRandomId();
            existAccountById = accountDAO.searchExistAccountById(id);
        }
        long newAccount = accountDAO.createNewAccount(id, ownerId, type, depositAmount);
        transactionService.createNewTransactionHistory(newAccount,newAccount,depositAmount,TransactionType.CREATE_NEW_ACCOUNT.getTypeIndex());
    }

    public void updateAccountBalance(Account account,Double amount,int type) throws SQLException {
        accountDAO.updateBalance(account.getId(),account.getBalance());
        transactionService.createNewTransactionHistory(account.getId(),account.getId(),amount,type);
    }

    public void paySystemFee(long accountId) throws SQLException {
        // pay system fee
        transactionService.createNewTransactionHistory(accountId,accountId,1,TransactionType.SYSTEM_FEE.getTypeIndex());
    }


    public void transaction(long sendId,Double amount,long receiveId) throws SQLException {
        accountDAO.transfer(sendId,receiveId,amount);
        transactionService.createNewTransactionHistory(sendId,receiveId,amount,TransactionType.TRANSACTION.getTypeIndex());
    }

    public void transfer(long sendId,long receiveId,double amount) throws SQLException {
        accountDAO.transfer(sendId,receiveId,amount);
        transactionService.createNewTransactionHistory(sendId,receiveId,amount,TransactionType.TRANSFER.getTypeIndex());
    }

    public void updateAccountProfit(long accountId, double profit){
        accountDAO.updateAccountProfit(accountId,profit);
    }

    public double getAccountProfit(long accountID) throws SQLException {
        return  accountDAO.getAccountProfit(accountID);
    }



}
