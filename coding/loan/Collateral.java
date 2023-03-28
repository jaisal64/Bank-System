package coding.loan;

/**
 * @Author: Jun Zhu
 * @Date: 12/12/2022 6:33 PM
 * @Description: TODO
 */
public class Collateral {
    private int id;
    private String name;
    private double price;

    public Collateral(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
