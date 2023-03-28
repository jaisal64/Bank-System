package Backend.loan;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:42 PM
 * @Description: Loan Entity
 */
public class Loan {
    private int id;
    private String customerID;
    private Collateral collateral;
    private String date;

    public Loan(int id, String customerID, Collateral collateral, String date) {
        this.id = id;
        this.customerID = customerID;
        this.collateral = collateral;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Collateral getCollateral() {
        return collateral;
    }

    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
