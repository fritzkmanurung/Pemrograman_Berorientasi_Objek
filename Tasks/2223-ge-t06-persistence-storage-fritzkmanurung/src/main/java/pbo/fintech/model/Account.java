package pbo.fintech.model;

import javax.persistence.*;

@Entity
@Table(name="Account")
public class Account {
    @Id
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "owner", nullable = false, length = 20)
    private String owner;
    @Column(name = "balance", nullable = false)
    private double balance;

    

    public Account() {
        //empty
    }

    public Account(String name, String owner, double balance) {
        this.name = name;
        this.owner = owner;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
