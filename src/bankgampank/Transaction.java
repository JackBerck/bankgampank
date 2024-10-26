package bankgampank;

import java.util.Date;

public class Transaction {
    private int id;
    private int userId;
    private String transactionType;
    private double amount;
    private int targetUserId;
    private Date date;

    public Transaction(int id, int userId, String transactionType, double amount, int targetUserId, Date date) {
        this.id = id;
        this.userId = userId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.targetUserId = targetUserId;
        this.date = date;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getTargetUserId() { return targetUserId; }
    public void setTargetUserId(int targetUserId) { this.targetUserId = targetUserId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
