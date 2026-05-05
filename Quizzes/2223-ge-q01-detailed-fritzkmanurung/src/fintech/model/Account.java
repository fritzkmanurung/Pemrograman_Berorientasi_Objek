package fintech.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Account {
    private String id;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.balance += transaction.getAmount();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("|").append(name).append("|").append(balance).append("\n");
        transactions.stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .forEach(t -> sb.append(t.toString()).append("\n"));
        return sb.toString();
    }
}