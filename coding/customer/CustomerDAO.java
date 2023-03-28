package coding.customer;

import Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 1:25 AM
 * @Description: TODO
 */
public class CustomerDAO {
    private DBUtil db;
    public CustomerDAO(){
        db = new DBUtil();
    }

    public Customer findCustomerById(String id) throws SQLException {
        Object[] para = new Object[]{id};
        String sql = "select * from customer where id = ?";
        ResultSet result = db.select(sql, para);
        while (result.next()) {
            String name = result.getString("name");
            String password = result.getString("password");
            Integer phoneNum = result.getInt("phoneNum");
            return new Customer(name, phoneNum, password, id);
        }
        return null;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String sql = "select * from customer";
        ResultSet result = db.select(sql, new Object[]{});
        List<Customer> customers = new ArrayList<>();
        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String password = result.getString("password");
            Integer phoneNum = result.getInt("phoneNum");
            customers.add(new Customer(name, phoneNum, password, id));
        }
        return customers;
    }

    public Boolean createNewCustomer(Customer customer){
        Object[] para = new Object[]{customer.getId(),customer.getName(),customer.getPassword(),customer.getPhoneNum()};
        String sql = "insert into customer(id,`name`,`password`,phoneNum) values(?,?,?,?)";
        int update = db.update(sql, para);
        if (update==0){
            return false;
        }
        return true;
    }

    public Customer findCustomerByIdPassword(String id, String password) throws SQLException {
        Object[] para = new Object[]{id,password};
        String sql = "select * from customer where id = ? and `password` = ?";
        ResultSet result = db.select(sql, para);
        if (result.next()){
            String name = result.getString("name");
            Integer phoneNum = result.getInt("phoneNum");
            return new Customer(name,phoneNum,password,id);
        }
        return null;
    }

    public Boolean updatePassword(String id, String password){
        Object[] para = new Object[]{password,id};
        String sql = "update customer set `password` = ? where id = ?";
        int update = db.update(sql, para);
        if (update==0){
            return false;
        }
        return true;
    }

    public Boolean updatePhoneNum(String id, String phoneNum){
        Object[] para = new Object[]{phoneNum,id};
        String sql = "update customer set phoneNum = ? where id = ?";
        int update = db.update(sql, para);
        if (update==0){
            return false;
        }
        return true;
    }

}
