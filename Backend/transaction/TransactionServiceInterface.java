package Backend.transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:06 AM
 * @Description: TODO
 */
public interface TransactionServiceInterface {
    public void createNewTransactionHistory(long senderId,long receiverId,double amount, int type) throws SQLException;
    public List<TransactionHistory> getTransactionsByDate(String date) throws SQLException;
    public List<TransactionHistory> getAllTransactions() throws SQLException;
    public boolean searchExistedById(long id) throws SQLException;
    public List<TransactionHistory> getAccountTransaction(Long accountId) throws SQLException;
}
