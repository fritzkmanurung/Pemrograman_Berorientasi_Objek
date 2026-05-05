package fintech.model;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */

public class Transaction {

    private static int sequence = 0;
    final String name;
    private Double balance = 0.0;

    private int id;
    private Account account;
    private double amount;
    private String postedAt;
    private String note;
   

    public Transaction(Account account, double amount, String postedAt, String note) {

        sequence++;
        this.name = account.getName();
        this.id = sequence;
        this.account = account;
        this.amount = amount;
        this.postedAt = postedAt;
        this.note = note;
        this.balance = account.getBalance();
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String detail() {
        return String.format("%s|%s|%.1f|%s|%s", id, account.getName(), balance, postedAt, note);
    }

    @Override
    public String toString() {
        return id + "|" +
            account.getName() + "|" +
            amount + "|" +
            postedAt + "|" +
            note + "|" +
            account.getBalance();
    }
}