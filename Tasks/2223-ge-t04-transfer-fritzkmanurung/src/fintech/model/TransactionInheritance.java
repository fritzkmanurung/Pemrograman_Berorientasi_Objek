package fintech.model;

public class TransactionInheritance extends Transaction {

    private String senderName;
    private String receiverName;

    public TransactionInheritance(Account sender, String receiverName2, double amount, String postedAt, String note) {
        super(sender, amount, postedAt, note);
        this.senderName = sender.getName();
    }


    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    @Override
    public String toString() {
        return super.toString() + "|" +
                senderName + "|" +
                receiverName;
    }
}
