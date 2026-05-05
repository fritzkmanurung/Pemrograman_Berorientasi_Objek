package fintech.model;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */
public class Transaction {
    // transaction objects sequence
    private static int sequence = 0;

    private int id;
    private Account account;
    private double amount;
    private String postedAt;
    private String note;

    public Transaction(Account account, double amount, String postedAt, String note) {
        sequence++;
        this.id = sequence;
        this.account = account;
        this.amount = amount;
        this.postedAt = postedAt;
        this.note = note;
    }

    public double getAmount() {
        return amount;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public String detail() {
        return id + "|" +
               account.getName() + "|" +
               amount + "|" +
               postedAt + "|" +
               note;
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