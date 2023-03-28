package Backend.currency;

import Backend.account.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/14/2022 6:02 AM
 * @Description: TODO
 */
public interface CurrencyServiceInterface {
    public void sellCurrency(Currency currency, Account account) throws SQLException;
    public List<Currency> getAllCurrencyList() throws SQLException;
    public List<Currency> getAccountCurrencyList(long accountId) throws SQLException;
    public void buyCurrency(Currency currency, Account account) throws SQLException;
}
