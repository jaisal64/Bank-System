package Backend.loan;

import Backend.Enum.TransactionType;
import Backend.account.AccountService;
import Backend.customer.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/12/2022 6:58 PM
 * @Description: Loan Service
 */
public class LoanService implements LoanServiceInterface {
    private LoanDAO loanDAO;
    private AccountService accountService;

    public LoanService(){
        loanDAO = new LoanDAO();
        accountService = new AccountService();
    }

    public List<Collateral> getAllCollateral() throws SQLException {
        return  loanDAO.getAllCollateral();
    }

    public void takeLoan(Customer customer, int collateralID,double collateralPrice) throws SQLException {
        loanDAO.insertNewLoan(customer,collateralID);
        customer.getCheckingAccount().setBalance(customer.getCheckingAccount().getBalance() + collateralPrice);
        accountService.updateAccountBalance(customer.getCheckingAccount(),collateralPrice, TransactionType.TAKE_LOAN.getTypeIndex());
    }

    public void returnLoan(Customer customer, int loanId,double collateralPrice) throws SQLException {
        loanDAO.payLoan(loanId);
        customer.getCheckingAccount().setBalance(customer.getCheckingAccount().getBalance() - collateralPrice);
        accountService.updateAccountBalance(customer.getCheckingAccount(),collateralPrice, TransactionType.RETURN_LOAN.getTypeIndex());
    }

    public List<Loan> getCustomerLoan(String customerId) throws SQLException {
        return loanDAO.getCustomerLoan(customerId);
    }

    public List<Loan> getAllLoans() throws SQLException {
        return loanDAO.getAllLoans();
    }
}
