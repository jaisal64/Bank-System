package Backend.customer;

import Backend.*;
import Backend.account.CheckingAccount;
import Backend.account.SavingAccount;
import Backend.account.SecurityAccount;
import Backend.loan.Loan;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: Customer Entity
 */
public class Customer extends User {
    SecurityAccount securityAccount;
    SavingAccount savingAccount;
    CheckingAccount checkingAccount;
    List<Loan> loans;

    public Customer(String name, Integer phoneNum, String password, String id) {
        super(name, phoneNum, password, id);
    }

    public SecurityAccount getSecurityAccount() {
        return securityAccount;
    }

    public void setSecurityAccount(SecurityAccount securityAccount) {
        this.securityAccount = securityAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
