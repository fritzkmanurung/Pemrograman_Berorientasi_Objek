package fintech.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Transaction {
    private int id;
    private String accountId;
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(int id, String accountId, double amount, LocalDateTime timestamp, String description) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + "|" + accountId + "|" + amount + "|" + timestamp.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "|" + description;
    }
}
