package ca.centennialcollege;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void showForm() {
        clearScreen();
        System.out.println("Form" +
                "\n[1] Register" +
                "\n[2] Login" +
                "\n[3] Quit");
    }
    public static void showOperations(Account account, int num_operations) {
        clearScreen();
        System.out.println(account +
                "\nOperation: " + num_operations +
                "\n[1] Withdraw" +
                "\n[2] Deposit" +
                "\n[3] Logout");
    }

    public static double amountInput(String operationType) {
        Scanner scanner = new Scanner(System.in);
        double amount = 0;
        while (true) {
            clearScreen();
            try {
                System.out.print(operationType +
                        "\nAmount Input: " );
                amount = Double.parseDouble(scanner.next());
                if (!(amount < 1)) {
                    System.out.println("Success: Valid input");
                    break;
                } else {
                    System.out.println("Error: Please input an amount greater than 1");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Please input a valid input");
            }
        }
        return amount;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Account register(int account_id) {
        // Variables
        String fname;
        String lname;
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);

        // Form
        clearScreen();
        System.out.print("Registration Form" +
                "\nFirst Name: ");
        fname =  scanner.nextLine().trim();
        System.out.print("\nLast Name: ");
        lname = scanner.nextLine().trim();
        System.out.print("\nEmail: ");
        email = scanner.nextLine().trim().toLowerCase();
        System.out.print("\nPassword: ");
        password = scanner.nextLine();

        // Return
        return new Account(account_id, fname, lname, password, email, 0);
    }

    public static Account login(ArrayList<Account> accountList) {
        // Variables
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);

        // Form
        clearScreen();
        System.out.print("Login Form" +
                "\nEmail: ");
        email =  scanner.nextLine().trim().toLowerCase();
        System.out.print("\nPassword: ");
        password = scanner.nextLine();

        for (Account account : accountList.toArray(new Account[0])) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                clearScreen();
                System.out.println("Login Successful" +
                        "\nWelcome, " + account);
                return account;
            }
        }


        System.out.print("Error: Incorrect email or password");

        // Return
        return null;
    }
}
