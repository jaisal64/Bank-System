package coding.loan;

import Utils.DBUtil;
import coding.customer.Customer;
import coding.transaction.TransactionHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/12/2022 6:36 PM
 * @Description: TODO
 */
public class LoanDAO {

    private DBUtil dbUtil;

    public LoanDAO(){
        dbUtil = new DBUtil();
    }

    public List<Collateral> getAllCollateral() throws SQLException {
        String sql = "select * from collateral";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{});
        List<Collateral> list = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String collateralName = resultSet.getString("collateralName");
            double loanPrice = resultSet.getDouble("loanPrice");
            list.add(new Collateral(id,collateralName,loanPrice));
        }
        return list;
    }

    public void insertNewLoan(Customer customer, int collateralID){
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(currentDate);
        String sql = "insert into loan (customerId,collateralId,date) values(?,?,?)";
        Object[] para = new Object[]{customer.getId(),collateralID,date};
        dbUtil.update(sql,para);
    }

    public List<Loan> getCustomerLoan(String customerId) throws SQLException {
        Object[] para = new Object[]{customerId};
        String sql = "select * from loan where customerId = ?";
        ResultSet resultSet = dbUtil.select(sql, para);
        List<Loan> loanList = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            int collateralId = resultSet.getInt("collateralId");
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("date"));
            sql = "select * from collateral where id = ?";
            ResultSet select = dbUtil.select(sql, new Object[]{collateralId});
            Collateral collateral = null;
            if (select.next()){
                String collateralName = select.getString("collateralName");
                double loanPrice = select.getDouble("loanPrice");
                collateral = new Collateral(collateralId,collateralName,loanPrice);
            }
            loanList.add(new Loan(id,customerId,collateral,date));
        }
        return loanList;
    }

    public List<Loan> getAllLoans() throws SQLException {
        String sql = "select * from loan";
        ResultSet resultSet = dbUtil.select(sql, new Object[]{});
        List<Loan> loanList = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String customerId = resultSet.getString("customerId");
            int collateralId = resultSet.getInt("collateralId");
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp("date"));
            sql = "select * from collateral where id = ?";
            ResultSet select = dbUtil.select(sql, new Object[]{collateralId});
            Collateral collateral = null;
            if (select.next()){
                String collateralName = select.getString("collateralName");
                double loanPrice = select.getDouble("loanPrice");
                collateral = new Collateral(collateralId,collateralName,loanPrice);
            }
            loanList.add(new Loan(id,customerId,collateral,date));
        }
        return loanList;
    }

    public void payLoan(long id){
        String sql = "delete from loan where id = ?";
        dbUtil.update(sql,new Object[]{id});
    }
}
