package fintech.model;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */

public class TransactionInheritance extends Transaction {

    private String receiverName;

    public TransactionInheritance(Account sender, String receiverName, double amount, String postedAt, String note) {
        super(sender, amount, postedAt, note);
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    @Override
    public String toString() {
        return super.toString() + "|" +
                getBalance();
    }
}