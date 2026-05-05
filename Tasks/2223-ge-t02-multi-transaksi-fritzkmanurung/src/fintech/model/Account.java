package fintech.model;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Account {
    private String name;
    private String owner;
    private double balance;

    public Account(String owner, String name) {
        this.owner = owner;
        this.name = name;
        this.balance = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + "|" + owner + "|" + balance;
    }

}