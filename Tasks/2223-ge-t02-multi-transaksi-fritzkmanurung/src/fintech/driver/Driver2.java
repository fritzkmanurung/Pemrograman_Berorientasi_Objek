package fintech.driver;

import fintech.model.Account;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */
public class Driver2 {

    public static void main(String[] _args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String arrsave[] = new String[20];
        int k = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            } else {
                String[] inputArr = input.split("#");

                if (inputArr[0].equals("create-account")) {
                    String owner = inputArr[1];
                    String name = inputArr[2];
                    Account newAccount = new Account(owner, name);
                    accounts.add(newAccount);

                } else if (inputArr[0].equals("find-account")) {
                    inputArr[1] = inputArr[1].toLowerCase();

                    for (Account account : accounts) {
                        if (account.getName().toLowerCase().equals(inputArr[1])) {
                            arrsave[k] = account.toString();
                            k++;
                        }
                    }
                }
            }

        }

        for (Account account : accounts) {
            System.out.println(account.toString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(arrsave[i]+"\n");
        }
    }
}
