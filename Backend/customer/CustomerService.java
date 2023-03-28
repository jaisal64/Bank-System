package Backend.customer;

import Backend.account.AccountService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 1:25 AM
 * @Description: Customer Service
 */
public class CustomerService implements CustomerServiceInterface {

    private CustomerDAO customerDAO;
    private AccountService accountService;
    public CustomerService(){
        customerDAO = new CustomerDAO();
        accountService = new AccountService();
    }

    public Customer findCustomerById(String id) throws SQLException {
        Customer customer =  customerDAO.findCustomerById(id);
        if (customer!=null){
            accountService.getCustomerAccountInfo(customer);
        }
        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public Boolean register(Customer customer){
        return customerDAO.createNewCustomer(customer);
    }

    public Customer getCustomerData(String id, String password) throws SQLException {
        Customer customer = customerDAO.findCustomerByIdPassword(id, password);
        if (customer!=null){
            accountService.getCustomerAccountInfo(customer);
        }
        return customer;

    }

    public Boolean updateCustomerPassword(String id, String password){
        return customerDAO.updatePassword(id,password);
    }

    public Boolean updateCustomerPhoneNum(String id, String phoneNuM){
        return customerDAO.updatePhoneNum(id,phoneNuM);
    }
}
