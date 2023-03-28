package Backend.transaction;

import Utils.CommonUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/4/2022 4:21 AM
 * @Description: TODO
 */
public class TransactionService implements TransactionServiceInterface {

    private TransactionHistoryDAO transactionDAO;
    public TransactionService(){
        transactionDAO = new TransactionHistoryDAO();
    }

    public void createNewTransactionHistory(long senderId,long receiverId,double amount, int type) throws SQLException {
        Long id = 0L;
        Boolean existAccountById = true;
        while (existAccountById){
            id =  CommonUtil.getRandomId();
            existAccountById = searchExistedById(id);
        }
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(currentDate);
        TransactionHistory transaction = new TransactionHistory(id, amount, senderId, receiverId, date, type);
        transactionDAO.createNewTransactionHistory(transaction);
    }

    public List<TransactionHistory> getTransactionsByDate(String date) throws SQLException {
        return transactionDAO.getTransactionsByDate(date);
    }

    public List<TransactionHistory> getAllTransactions() throws SQLException {
        return transactionDAO.getAllTransactions();
    }

    public boolean searchExistedById(long id) throws SQLException {
        return transactionDAO.searchExistedById(id);
    }

    public List<TransactionHistory> getAccountTransaction(Long accountId) throws SQLException {
        return transactionDAO.getAccountTransaction(accountId);
    }
}
