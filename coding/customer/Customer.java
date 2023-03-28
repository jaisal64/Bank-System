package coding.customer;

import coding.*;
import coding.account.CheckingAccount;
import coding.account.SavingAccount;
import coding.account.SecurityAccount;
import coding.loan.Loan;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: TODO
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
