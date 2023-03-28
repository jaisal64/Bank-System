package Backend.account;

import Backend.transaction.TransactionHistory;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: Account Entity
 */
public abstract class Account {
    protected Long id;
    protected Double balance;
    protected List<TransactionHistory> transactionHistory;

    public Account(Long id, Double balance, List<TransactionHistory> transactionHistory) {
        this.id = id;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<TransactionHistory> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionHistory> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
