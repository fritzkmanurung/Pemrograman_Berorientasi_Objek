package fintech.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Account {
    private static int nextId = 1;
    private int id;
    private String owner;
    private String name;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String owner, String name) {
        this.id = nextId++;
        this.owner = owner;
        this.name = name;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if (transaction.getSender() == this) {
            this.balance -= transaction.getAmount();
        } else {
            this.balance += transaction.getAmount();
        }
    }

    public String detail() {
        String output = String.format("%s|%s|%.1f", this.name, this.owner, this.balance);
        // Mengurutkan transaksi berdasarkan waktu posting (postedAt)
        Collections.sort(transactions, new TransactionComparator());

        for (Transaction transaction : transactions) {
            String postedAt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(transaction.getPostedAt());
            if (transaction.getSender() == this && transaction.getRecipient() == null) {
                output += String.format("\n%d|%s|%.1f|%s|%s", transaction.getId(), transaction.getSender().getName(), transaction.getAmount(), postedAt, transaction.getNote());
            } else {
                output += String.format("\n%d|%s|%s|%.1f|%s|%s", transaction.getId(), transaction.getSender().getName(), transaction.getRecipient().getName(), transaction.getAmount(), postedAt, transaction.getNote());
            }
        }
        return output;
    }
    

    // Comparator untuk mengurutkan transaksi berdasarkan postedAt
    private static class TransactionComparator implements Comparator<Transaction> {
        @Override
        public int compare(Transaction t1, Transaction t2) {
            return t1.getPostedAt().compareTo(t2.getPostedAt());
        }
    }


    public String toString() {
        return String.format(this.name + "|" + this.owner + "|" + this.balance);
    }

    public static void sortByName(ArrayList<Account> accounts) {
        accounts.sort(Comparator.comparing(Account::getName));
    }

    
    public void setBalance(double balance) {
        this.balance = balance;
    }
}