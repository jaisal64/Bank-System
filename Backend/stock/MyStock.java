package Backend.stock;

/**
 * @Author: Jun Zhu
 * @Date: 12/9/2022 7:16 AM
 * @Description: MyStock Entity
 */
public class MyStock extends Stock{

    private int stockId;
    private double buyingPrice;
    private int buyingAmount;


    public MyStock(int id, String name, double price, int stockId, double buyingPrice, int buyingAmount) {
        super(id, name, price);
        this.stockId = stockId;
        this.buyingPrice = buyingPrice;
        this.buyingAmount = buyingAmount;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getBuyingAmount() {
        return buyingAmount;
    }

    public void setBuyingAmount(int buyingAmount) {
        this.buyingAmount = buyingAmount;
    }
}
