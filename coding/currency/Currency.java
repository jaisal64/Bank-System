package coding.currency;

/**
 * @Author: Jun Zhu
 * @Date: 12/10/2022 5:12 PM
 * @Description: TODO
 */
public class Currency {
    private int id;
    private String name;
    private double rate;
    private int amount;

    public Currency(int id, String name, double rate, int amount) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
