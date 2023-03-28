package Backend.account;

import Backend.stock.MyStock;
import Backend.transaction.TransactionHistory;

import java.util.List;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: Security Account
 */
public class SecurityAccount extends Account{
    private List<MyStock> ownStack;
    private double realizedProfit;
    private double unrealizedProfit;

    public SecurityAccount(Long id, Double balance, List<TransactionHistory> transactionHistory) {
        super(id, balance, transactionHistory);
    }


    public SecurityAccount(Long id, Double balance, List<TransactionHistory> transactionHistory, List<MyStock> ownStack, double realizedProfit, double unrealizedProfit) {
        super(id, balance, transactionHistory);
        this.ownStack = ownStack;
        this.realizedProfit = realizedProfit;
        this.unrealizedProfit = unrealizedProfit;
    }

    public List<MyStock> getOwnStack() {
        return ownStack;
    }

    public void setOwnStack(List<MyStock> ownStack) {
        this.ownStack = ownStack;
    }

    public double getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(double realizedProfit) {
        this.realizedProfit = realizedProfit;
    }

    public double getUnrealizedProfit() {
        return unrealizedProfit;
    }

    public void setUnrealizedProfit(double unrealizedProfit) {
        this.unrealizedProfit = unrealizedProfit;
    }
}
