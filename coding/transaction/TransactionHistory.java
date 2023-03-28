package coding.transaction;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:37 PM
 * @Description: TODO
 */
public class TransactionHistory {
    private Long id;
    private Double amount;
    private Long senderId;
    private Long receiverId;
    private String date;
    private int transactionType;


    public TransactionHistory(Long id, Double amount, Long senderId, Long receiverId, String date, int transactionType) {
        this.id = id;
        this.amount = amount;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.date = date;
        this.transactionType = transactionType;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
