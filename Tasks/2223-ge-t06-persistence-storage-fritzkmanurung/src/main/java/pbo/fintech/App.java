package pbo.fintech;

import javax.persistence.*;
import pbo.fintech.model.*;
import java.util.*;

/**
 * 12S21014 - Fritz Kevin Manurung
 * 12S21060 - Glory Natasya Hutahaean
 * 
 * To compile:
 * mvn clean compile assembly:single
 * 
 * To run:
 * java -cp .\target\fintech-1.0-SNAPSHOT-jar-with-dependencies.jar pbo.fintech.App
 */
public class App {
    public static void main(String[] args) {

        // Create an EntityManagerFactory object to establish a connection with the database
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fintech_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("---")) {
                break;
            }

            String[] data = input.split("#");
            String cmd = data[0];

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            try {
                // Perform CRUD operations using Entity Manager
                 if (cmd.equals("create-account")) {
                    TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.name=:name", Account.class);
                    query.setParameter("name", data[2]);
                    List<Account> accounts = query.getResultList();

                    if (accounts.isEmpty()) {
                        Account account = new Account(data[2], data[1], 0);
                        entityManager.persist(account);
                        System.out.println(account.toString());
                    } else {
                    }
                } else if (cmd.equals("show-accounts")) {
                    TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a ORDER BY a.name ASC", Account.class);
                    List<Account> accounts = query.getResultList();
    
                    for (Account account : accounts) {
                        System.out.println(account.toString());
                    }
                } else if (cmd.equals("remove-account")) {
                    TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.name=:name", Account.class);
                    query.setParameter("name", data[1].toLowerCase());
                    List<Account> accounts = query.getResultList();

                    if (!accounts.isEmpty()) {
                        Account account = accounts.get(0);
                        entityManager.remove(account);
                    }
                }

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Error: " + e.getMessage());
            }
        }
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Account a").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        scanner.close();    }
}
