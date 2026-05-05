package fintech.driver;

import fintech.model.Account;

import java.util.Scanner;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Hutahaean
 */
public class Driver1 {
    public static void main(String[] _args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        String owner = scanner.nextLine();

        String name = scanner.nextLine();

        Account account = new Account(owner, name);

        System.out.println(account);
        

        scanner.close();
    }
}