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

            switch (command) {
                case "create-account":
                    if (findAccount(data[1]) == null) {

                        Account account = new Account(data[0], data[1]);

                        System.out.println(account.toString());

                        accounts.add(account);
                    }
                    break;

                case "create-transaction":
                tmp = findAccount(data[0]);
                for (Account account: accounts) {
                    if (tmp != null) {
                        double amount = Double.parseDouble(data[1]);
                        if (account.getBalance() + amount < 0){
                            break; // Tambahkan break jika saldo tidak mencukupi
                        } else {
                            Transaction transaction = new Transaction(tmp, amount, data[2], data[3]);
                            tmp.addTransaction(transaction);
                        }
                    }
                }
                break;
                
                    

                case "show-account":
                    tmp = findAccount(data[0]);
                    

                    if (tmp != null)

                        System.out.println(tmp.detail());
                    break;

                default:
                    break;
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
