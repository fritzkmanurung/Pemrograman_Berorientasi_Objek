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
                if (findAccount(data[1]) == null) {
                    Account account = new Account(data[0], data[1]);

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
                            Transaction transaction = new Transaction(tmp, amount, data[2], data[3]);
                            tmp.addTransaction(transaction);
                            System.out.println(transaction);
                        }
                    }
                } else if(data.length == 6){
                    Account senderAccount = findAccount(data[1]);
                    Account receiverAccount = findAccount(data[2]);
                    String receiverName = data[2];

                    if (senderAccount != null && receiverAccount != null) {
                        double amount = Double.parseDouble(data[3]);
                        double newBalance = senderAccount.getBalance() + amount;
                
                        if (newBalance >= 0 && amount >= 0) {
                            Transaction transaction = new TransactionInheritance(senderAccount, receiverName ,amount, data[2], data[3]);
                            receiverAccount.addTransaction(transaction);
                            senderAccount.reducedBalance(transaction);
                        }
                    }
                }
            } else if (command.equals("show-accounts")) {
                ArrayList<Account> population = new ArrayList<>(accounts);

                population.sort(Comparator.comparing(left -> left.getName().toLowerCase()));

                for (Account account: population)
                    System.out.println(account.detail());
            } else if (command.equals("show-account")) {
                tmp = findAccount(data[0]);
            
                if (tmp != null) {
                    System.out.println("Account Name: " + tmp.getName());
                    System.out.println("Transaction History:");
                    ArrayList<Transaction> transactions = tmp.getTransactionHistory();
                    if (transactions.size() > 0) {
                        for (Transaction t : transactions) {
                            System.out.println(t);
                        }
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
}