package coding.transaction;

import Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/4/2022 4:21 AM
 * @Description: TODO
 */
public class TransactionHistoryDAO {
    private DBUtil dbUtil;
    public TransactionHistoryDAO(){
        dbUtil = new DBUtil();
    }

    public Boolean searchExistedById(Long id) throws SQLException {
        Object[] para = new Object[]{id};
        String sql = "select * from transactionHistory where id = ?";
        ResultSet resultSet = dbUtil.select(sql, para);
        if (resultSet.next()){
            return true;
        }
        return false;
    }

    public Boolean createNewTransactionHistory(TransactionHistory transaction){
        Object[] para = new Object[]{transaction.getId(),transaction.getAmount(),transaction.getSenderId(),transaction.getReceiverId(),transaction.getDate(),transaction.getTransactionType()};
        String sql = "insert into transactionHistory(id,amount,senderId,receiverId,date,type) values (?,?,?,?,?,?)";
        int update = dbUtil.update(sql, para);
        if (update==0){
            return false;
        }else{
            return true;
        }
    }

    public List<TransactionHistory> getAccountTransaction(Long accountId) throws SQLException {
        Object[] para = new Object[]{accountId,accountId};
        String sql = "select * from transactionHistory where senderId = ? or receiverId = ?";
        ResultSet resultSet = dbUtil.select(sql, para);
        List<TransactionHistory> transactionList = new ArrayList<>();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            double amount = resultSet.getDouble("amount");
            long senderId = resultSet.getLong("senderId");
            long receiverId = resultSet.getLong("receiverId");
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("date"));
            int type = resultSet.getInt("type");
            TransactionHistory transaction = new TransactionHistory(id,amount,senderId,receiverId,date,type);
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public List<TransactionHistory> getAllTransactions() throws SQLException {
        String sql = "select * from transactionHistory";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{});
        List<TransactionHistory> transactionList = new ArrayList<>();
        while(resultSet.next()){
            long id = resultSet.getLong("id");
            double amount = resultSet.getDouble("amount");
            long senderId = resultSet.getLong("senderId");
            long receiverId = resultSet.getLong("receiverId");
            String transactionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("date"));
            int type = resultSet.getInt("type");
            TransactionHistory transaction = new TransactionHistory(id,amount,senderId,receiverId,transactionDate,type);
            transactionList.add(transaction);
        }
        return transactionList;
    }

    public List<TransactionHistory> getTransactionsByDate(String date) throws SQLException {
        String parameterizedDate = String.join("%",date,"%");
        Object[] para = new Object[]{parameterizedDate};
        String sql = "select * from transactionHistory where date like ?";
        ResultSet resultSet = dbUtil.select(sql, para);
        List<TransactionHistory> transactionList = new ArrayList<>();
        while(resultSet.next()){
            long id = resultSet.getLong("id");
            double amount = resultSet.getDouble("amount");
            long senderId = resultSet.getLong("senderId");
            long receiverId = resultSet.getLong("receiverId");
            String transactionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("date"));
            int type = resultSet.getInt("type");
            TransactionHistory transaction = new TransactionHistory(id,amount,senderId,receiverId,transactionDate,type);
            transactionList.add(transaction);
        }
        return transactionList;
    }
}
