package Backend.loan;

import Backend.customer.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:03 AM
 * @Description: TODO
 */
public interface LoanServiceInterface {
    public List<Collateral> getAllCollateral() throws SQLException;
    public void takeLoan(Customer customer, int collateralID, double collateralPrice) throws SQLException;
    public void returnLoan(Customer customer, int loanId,double collateralPrice) throws SQLException;
    public List<Loan> getCustomerLoan(String customerId) throws SQLException;
    public List<Loan> getAllLoans() throws SQLException;
}
