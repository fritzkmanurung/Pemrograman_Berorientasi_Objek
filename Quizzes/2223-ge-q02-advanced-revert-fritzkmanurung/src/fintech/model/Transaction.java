package fintech.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private static int nextId = 1;
    private int id;
    private Account sender;
    private Account recipient;
    private double amount;
    private Date postedAt;
    private String note;

    public Transaction(Account sender, double amount, String postedAt, String note) {
        this.id = nextId++;
        this.sender = sender;
        this.amount = amount;
        this.note = note;
        try {
            this.postedAt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(postedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Transaction(Account sender, Account recipient, double amount, String postedAt, String note) {
        this.id = nextId++;
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.note = note;
        try {
            this.postedAt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(postedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        try {
            this.postedAt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(postedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNote() {
        return note;
    }


    public class RevertTransaction extends Transaction {
        public RevertTransaction(Account sender, Transaction originalTransaction, String postedAt) {
            super(sender, originalTransaction.getRecipient(), -originalTransaction.getAmount(), postedAt,
                    "REVERT: " + originalTransaction.getNote());
        }

        public RevertTransaction(Account sender, double amount, String postedAt, String note) {
            super(sender, null, -amount, postedAt, "REVERT: " + note);
        }
    }
}
