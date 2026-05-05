package fintech.driver;

import fintech.model.*;

import java.util.*;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */

public class Driver1 {

    private static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = new String();

        while (true) {
            input = scanner.nextLine();

            if (input.equals("---"))
                break;

            String[] data = input.split("#");

            String command = data[0];

            data = Arrays.copyOfRange(data, 1, data.length);

            Account tmp = null;

            if (command.equals("create-account")) {
                if (findAccount(data[0]) == null) {
                    Account account = new Account(data[1], data[0]);

                    System.out.println(account);

                    accounts.add(account);
                }
            } else if (command.equals("create-transaction")) {
                if(data.length == 5){
                    tmp = findAccount(data[0]);

                    if (tmp != null) {
                        double amount = Double.parseDouble(data[2]);
                        double newBalance = tmp.getBalance() + amount;

                        if (newBalance >= 0) {
                            Transaction transaction = new Transaction(tmp, amount, data[3], data[4]);
                            tmp.addTransaction(transaction);
                            System.out.println(transaction);
                        }else{
                            System.out.println("Insufficient balance. Transaction cancelled.");
                        }
                    }
                } else if(data.length == 6){
                    Account senderAccount = findAccount(data[0]);
                    Account receiverAccount = findAccount(data[1]);

                    if (senderAccount != null && receiverAccount != null) {
                        double amount = Double.parseDouble(data[3]);
                        double newBalance = senderAccount.getBalance() - amount;

                        if (newBalance >= 0 && amount >= 0) {
                            Transaction transaction = new Transaction("DEBIT", amount, data[4], data[5]);
                            receiverAccount.addTransaction(transaction);
                            senderAccount.reducedBalance(transaction);
                            System.out.println(transaction);
                        }else{
                            System.out.println("Insufficient balance. Transaction cancelled.");
                        }
                    }else{
                        System.out.println("Invalid account(s). Transaction cancelled.");
                    }
                }
            } else if (command.equals("show-account")) {
                tmp = findAccount(data[0]);

                if (tmp != null) {
                    System.out.println("Account Name: " + tmp.getName());
                    System.out.println("Balance: " + tmp.getBalance());
                    System.out.println("Transaction History:");
                    ArrayList<Transaction> transactions = tmp.getTransactionHistory();
                    if (transactions.size() > 0) {
                        for (Transaction transaction : transactions) {
                            System.out.println(transaction);
                        }
                    }
                }else{
                    System.out.println("Account not found.");
                }
            } else if (command.equals("revert-transaction")) {
                String accountName = data[0];
                String transactionId = data[1];
                String postedAt = data[2];
                String transactionNote = "REVERT: " + data[3];
                Transaction targetTransaction = getTransaction(accountName, transactionId, postedAt);
                if(targetTransaction != null) {
                    Account targetAccount = findAccount(accountName);
                    double reverseAmount = targetTransaction.getAmount() * -1;
                    if(targetTransaction.getType().equals("DEBIT")) {
                        Transaction reverseTransaction = new Transaction("CREDIT", reverseAmount, transactionNote, targetTransaction.getPostedAt());
                        targetAccount.addTransaction(reverseTransaction);
                        targetAccount.increasedBalance(reverseAmount);
                        System.out.println(targetAccount.getBalance());
                    } else {
                        Transaction reverseTransaction = new Transaction("DEBIT", reverseAmount, transactionNote, targetTransaction.getPostedAt());
                        targetAccount.addTransaction(reverseTransaction);
                        targetAccount.reducedBalance(reverseTransaction);
                        System.out.println(targetAccount.getBalance());
                    }
                }
            }
        }       
        scanner.close();
    }

    public static Account findAccount(String name) {
        for (Account account: accounts)
            if (account.getName().equalsIgnoreCase(name))
                return account;

        return null;
    }

    public static Transaction getTransaction(String accountName, String transactionId, String postedAt){
        Account account = findAccount(accountName);
        if(account!=null){
            ArrayList<Transaction> transactions = account.getTransactionHistory();
            for(Transaction transaction: transactions){
                if(transaction.getId().equals(transactionId) && transaction.getPostedAt().equals(postedAt)){
                    return transaction;
                }
            }
        }
        return null;
    }

}
