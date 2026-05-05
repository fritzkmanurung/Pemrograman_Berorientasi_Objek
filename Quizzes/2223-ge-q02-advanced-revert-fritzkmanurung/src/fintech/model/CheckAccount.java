package fintech.model;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

public class CheckAccount extends Account {
    private double minimumBalance;

    public CheckAccount(String owner, String name, double minimumBalance) {
        super(owner, name);
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction.getSender() == this && this.getBalance() - transaction.getAmount() < this.minimumBalance) {
            System.out.println("Transaction failed: Insufficient balance in CheckingAccount");
        } else {
            super.addTransaction(transaction);
        }
    }

    @Override
    public String detail() {
        String output = String.format("%s|%s|%.1f|%.1f", this.getName(), this.getOwner(), this.getBalance(), this.minimumBalance);
        Collections.sort(getTransactions(), new TransactionComparator());

        for (Transaction transaction : getTransactions()) {
            String postedAt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(transaction.getPostedAt());
            if (transaction.getSender() == this && transaction.getRecipient() == null) {
                output += String.format("\n%d|%s|%.1f|%s|%s", transaction.getId(), transaction.getSender().getName(), transaction.getAmount(), postedAt, transaction.getNote());
            } else {
                output += String.format("\n%d|%s|%s|%.1f|%s|%s", transaction.getId(), transaction.getSender().getName(), transaction.getRecipient().getName(), transaction.getAmount(), postedAt, transaction.getNote());
            }
        }
        return output;
    }

    private static class TransactionComparator implements Comparator<Transaction> {
        @Override
        public int compare(Transaction t1, Transaction t2) {
            return t1.getPostedAt().compareTo(t2.getPostedAt());
        }
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%.1f|%.1f", this.getName(), this.getOwner(), this.getBalance(), this.minimumBalance);
    }
}
