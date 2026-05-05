package fintech.driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import fintech.model.Account;
import fintech.model.Transaction;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Driver1 {

    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();
        int transactionCounter = 0;

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break;
            } else {
                String[] segments = line.split("#");
                String command = segments[0];

                if (command.equals("create-account")) {
                    String name = segments[1];
                    String id = segments[2].toLowerCase();
                    accounts.put(id, new Account(id, name));

                } else if (command.equals("create-transaction")) {
                    String accountId = segments[1].toLowerCase();
                    double amount = Double.parseDouble(segments[2]);
                    LocalDateTime timestamp = LocalDateTime.parse(segments[3], DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    String description = segments[4];
                    Account account = accounts.get(accountId);
                    Transaction transaction = new Transaction(++transactionCounter, accountId, amount, timestamp, description);
                    account.addTransaction(transaction);
                    
                } else if (command.equals("show-account")) {
                    String accountId = segments[1].toLowerCase();
                    Account account = accounts.get(accountId);
                    System.out.println(account.toString());
                }
            }

            scanner.close();
        }
    }
}