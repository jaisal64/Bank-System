package coding.account;

import coding.stock.MyStock;
import coding.stock.Stock;
import coding.transaction.TransactionHistory;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: TODO
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
