package Backend.account;

import Backend.Enum.CurrencyType;
import Backend.transaction.TransactionHistory;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: Saving Account
 */
public class SavingAccount extends Account{
    private Map<CurrencyType,Double> currency;

    public SavingAccount(Long id, Double balance, List<TransactionHistory> transactionHistory) {
        super(id, balance, transactionHistory);
    }

    public SavingAccount(Long id, Double balance, List<TransactionHistory> transactionHistory, Map<CurrencyType, Double> currency) {
        super(id, balance, transactionHistory);
        this.currency = currency;
    }

    public Map<CurrencyType, Double> getCurrency() {
        return currency;
    }

    public void setCurrency(Map<CurrencyType, Double> currency) {
        this.currency = currency;
    }
}
