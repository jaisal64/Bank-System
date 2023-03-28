package coding.account;

import coding.transaction.TransactionHistory;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: TODO
 */
public class CheckingAccount extends Account{
    public CheckingAccount(Long id, Double balance, List<TransactionHistory> transactionHistory) {
        super(id, balance, transactionHistory);


    }
}
