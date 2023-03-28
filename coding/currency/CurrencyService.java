package coding.currency;

import coding.Enum.TransactionType;
import coding.account.Account;
import coding.account.AccountService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/10/2022 5:19 PM
 * @Description: TODO
 */
public class CurrencyService {
    private CurrencyDAO currencyDAO;

    private AccountService accountService;

    public CurrencyService() {
        this.currencyDAO = new CurrencyDAO();
        this.accountService = new AccountService();
    }

    public void sellCurrency(Currency currency, Account account) throws SQLException {
        double totalIncome = currency.getAmount() * currency.getRate();
        account.setBalance(account.getBalance() + totalIncome);
        accountService.updateAccountBalance(account,totalIncome, TransactionType.SELL_CURRENCY.getTypeIndex());
        currencyDAO.removeCurrency(currency.getId(),account.getId());
    }

    public List<Currency> getAllCurrencyList() throws SQLException {
        return currencyDAO.getAllCurrencyList();
    }

    public List<Currency> getAccountCurrencyList(long accountId) throws SQLException {
        return currencyDAO.getAccountCurrencyList(accountId);
    }

    public void buyCurrency(Currency currency, Account account) throws SQLException {
        double totalCost = currency.getAmount() * currency.getRate();
        account.setBalance(account.getBalance() - totalCost);
        accountService.updateAccountBalance(account,totalCost, TransactionType.BUY_CURRENCY.getTypeIndex());
        if (currencyDAO.getCurrencyAmount(account.getId(),currency.getId())>=0){
            currencyDAO.updateCurrencyAmount(currency,account.getId());
        }else{
            currencyDAO.saveNewCurrency(currency,account.getId());
        }
    }


}
