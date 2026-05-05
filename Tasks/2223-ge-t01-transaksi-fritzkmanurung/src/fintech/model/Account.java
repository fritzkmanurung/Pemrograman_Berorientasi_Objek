package fintech.model;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Hutahaean
 */

public class Account {
    private final String owner;
    private final String name;
    private double balance;

    public Account(String owner, String name) {
        this.owner = owner;
        this.name = name;
        this.balance = 0.0;
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

    @Override
    public String toString() {
        return this.name + "|" + this.owner + "|" + this.balance;
    }

}