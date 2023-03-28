package coding;

import coding.customer.Customer;
import coding.customer.CustomerService;
import coding.loan.LoanService;
import coding.stock.Stock;
import coding.stock.StockService;
import coding.transaction.TransactionService;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: TODO
 */
public class BankManager extends User{

    private CustomerService customerService;
    private StockService stockService;
    private TransactionService transactionService;
    private LoanService loanService;

    public BankManager(String name, Integer phoneNum, String password, String id) {
        super(name, phoneNum, password, id);
        this.customerService = new CustomerService();
        this.stockService = new StockService();
        this.transactionService = new TransactionService();
        this.loanService = new LoanService();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public StockService getStockService() {
        return stockService;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }
}
