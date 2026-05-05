package fintech.model;
import java.util.*;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */

public class Account {
    private String owner;
    private String name;
    private double balance;
    private Transaction[] transactions;

    public Account(String owner, String name) {
        this.owner = owner;
        this.name = name;
        this.balance = 0.0;
        this.transactions = new Transaction[0];
    }

    public String getName() {
        return name;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {

        transactions = Arrays.copyOfRange(transactions, 0, transactions.length + 1);
        transactions[transactions.length - 1] = transaction;

        balance = balance + transaction.getAmount();
    }

    public void reducedBalance(Transaction transaction) {

        transactions = Arrays.copyOfRange(transactions, 0, transactions.length + 1);
        transactions[transactions.length - 1] = transaction;

        balance = balance - transaction.getAmount();
    }

    public String detail() {

        String detail = toString();

        Transaction[] _transactions = transactions;

        Arrays.sort(_transactions, Comparator.comparing(Transaction::getPostedAt));

        for (Transaction transaction: _transactions)
            detail += "\n" + transaction.detail();

        return detail;
    }

    @Override
    public String toString() {
        return name + "|" + owner + "|" + balance;
    }

    public Account getAccountName() {
        return null;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        ArrayList<Transaction> history = new ArrayList<>(Arrays.asList(transactions)); 
        return history;
    }
}
