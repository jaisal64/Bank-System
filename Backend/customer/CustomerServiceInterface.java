package Backend.customer;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:02 AM
 * @Description: TODO
 */
public interface CustomerServiceInterface {
    public Customer findCustomerById(String id) throws SQLException;
    public List<Customer> getAllCustomers() throws SQLException;
    public Boolean register(Customer customer);
    public Customer getCustomerData(String id, String password) throws SQLException;
    public Boolean updateCustomerPassword(String id, String password);
    public Boolean updateCustomerPhoneNum(String id, String phoneNuM);
}
