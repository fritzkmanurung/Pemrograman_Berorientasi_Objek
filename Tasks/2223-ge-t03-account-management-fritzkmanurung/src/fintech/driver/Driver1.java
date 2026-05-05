package fintech.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import fintech.model.*;

/**
 * @author 12S21014 Fritz Kevin Manurung
 * @author 12S21060 Glory Natasya Hutahaean
 */

public class Driver1 {
    public static void main(String[] args) {

        ArrayList<Account> accounts = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] data = input.split("#");
            String cmd = data[0];

            if (cmd.equals("create-account")) {
                String name = data[1].toLowerCase(); // membuat name menjadi lowercase
                String owner = data[2];
                boolean isDuplicate = false;
                for (Account account : accounts) {
                    if (account.getOwner().equals(owner)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (isDuplicate) {
                    
                } else {
                    Account account = new Account(owner, name);
                    accounts.add(account);
                    System.out.println(account.toString());
                }
            } else if (cmd.equals("show-accounts")) {
            Collections.sort(accounts, (a1, a2) -> a1.getName().compareTo(a2.getName()));
            for (Account accountt : accounts) {
                System.out.println(accountt.toString());
            }
        } else if (cmd.equals("remove-accounts")){
            Account account = null;
            for(Account a : accounts){
                if(a.getName().equals(data[1])){
                    account = a;
                    break;
                }
            }
            if(account != null){
                accounts.remove(account);
            }
        }
        }
    }
}
