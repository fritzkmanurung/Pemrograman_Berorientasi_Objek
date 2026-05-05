package fintech.driver;
import fintech.model.Account;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Driver1 {

    public static void main(String[] _args) {
        
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("---")) {
            String[] inputArr = input.split("#");

            if (inputArr[0].equals("create-account")) {
                String owner = inputArr[1];
                String name = inputArr[2];
                Account newAccount = new Account(owner, name);
                accounts.add(newAccount);
            }

            input = scanner.nextLine();
        }

        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

}