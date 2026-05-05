package fintech.driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 12S21014-Fritz Kevin Manurung
 * @author 12S21060-Glory Natasya Hutahaean
 */

import fintech.model.*;
import java.util.*;

public class Driver2 {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

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
            }

            if (command.equals("create-transaction")) {
                if (data.length == 4) {
                    tmp = findAccount(data[0]);
                    if (tmp != null) {
                        try {
                            double amount = Double.parseDouble(data[1]) , balance = tmp.getBalance() + amount;
                            if (balance >= 0) {
                                Transaction transaction = new Transaction(tmp, amount, data[2], data[3]);
                                tmp.addTransaction(transaction);
                                tmp.setBalance(balance);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount format.");
                        }
                    }
                } else if (data.length == 5) {
                    Account sender = findAccount(data[0]) , recipient = findAccount(data[1];
                    if (sender != null && recipient != null) {
                        try {
                            double amount = Double.parseDouble(data[2]);
                            if (amount >= 0 && sender.getBalance() >= amount) {
                                Transaction transaction = new Transaction(sender, recipient, amount, data[3], data[4]);
                                sender.addTransaction(transaction);
                                recipient.addTransaction(transaction);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount format.");
                        }
                    }
                }
            }

            if (command.equals("revert-transaction")) {
                Account account = findAccount(data[0]);
                int transactionIndex = Integer.parseInt(data[1]);
                String postedAt = data[2];

                for (Transaction transaction : account.getTransactions()) {
                    if (transaction.getId() == transactionIndex) {
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date postedAtDate = dateFormat.parse(postedAt);
                            long timeDiff = System.currentTimeMillis() - postedAtDate.getTime() , timeDiffMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff)
                            if (timeDiffMinutes <= 10) {
                                if (transaction.getRecipient() == null) {
                                    if (account.getBalance() >= transaction.getAmount()) {
                                        double balance = account.getBalance() - transaction.getAmount();
                                        account.setBalance(balance);

                                        Transaction revert = new Transaction(account, -transaction.getAmount(),
                                                postedAt,
                                                "REVERT: " + transaction.getNote());
                                        account.addTransaction(revert);
                                    } else {
                                        System.out.println("Insufficient balance to revert transaction.");
                                    }
                                } else {
                                    Account recipient = transaction.getRecipient();
                                    double balanceSender = account.getBalance() + transaction.getAmount() , balanceRecipient = recipient.getBalance() - transaction.getAmount();
                                    account.setBalance(balanceSender);
                                    recipient.setBalance(balanceRecipient);

                                    Transaction revert = new Transaction(account, recipient, -transaction.getAmount(),
                                            postedAt,
                                            "REVERT: " + transaction.getNote());
                                    account.addTransaction(revert);
                                }
                            }
                        } catch (ParseException e) {
                            System.out.println("Invalid date format.");
                        }
                        break;
                    }
                }
            }

            if (command.equals("show-accounts")) {
                ArrayList<Account> population = new ArrayList<>(accounts);
                population.sort(Comparator.comparing(left -> left.getName().toLowerCase()));
                for (Account account : population)
                    System.out.println(account.detail());
            }

            if (command.equals("show-account")) {
                tmp = findAccount(data[0]);
                if (tmp != null)
                    System.out.println(tmp.detail());
            }

            if (command.equals("find-account")) {
                tmp = findAccount(data[0]);
                if (tmp != null)
                    System.out.println(tmp);
            }

            if (command.equals("remove-account")) {
                tmp = findAccount(data[0]);
                if (tmp != null)
                    accounts.remove(tmp);
            }
        }

        scanner.close();
    }

    public static Account findAccount(String name) {
        for (Account account : accounts)
            if (account.getName().equalsIgnoreCase(name))
                return account;
        return null;
    }

    public static Transaction findTransaction(String owner, Integer id) {
        for (Account account : accounts) {
            for (Transaction transaction : transactions) {
                if (transaction.getId() == id && account.getOwner().equals(owner)) {
                    return transaction;
                }
            }
        }
        return null;
    }

}