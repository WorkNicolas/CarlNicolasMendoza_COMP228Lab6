package ca.centennialcollege;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    static int account_id = 1;
    static ArrayList<Account> accountList = new ArrayList<>();
    static Account currentAccount = null;
    static int num_operations = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        initialize();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int input;
            if (currentAccount == null) {
                Menu.showForm();
                input = Integer.parseInt(scanner.next());
                handleEnter(input);
            } else {
                finalOutput();
                if (num_operations == 3) break;
                Menu.showOperations(currentAccount, num_operations);
                input = Integer.parseInt(scanner.next());
                handleEntered(input);
            }
        }
        System.out.println("Final Output: " + currentAccount);
        System.out.println("Transactions done" +
                "\nPress ENTER to continue...");
        // Using nextLine() instead of next() to handle multiple lines of input
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void handleEnter(int keycode) {
        switch (keycode) {
            case 1 -> currentAccount = Menu.register(account_id);
            // Login
            case 2 -> {
                currentAccount = Menu.login(accountList);

            }
            // Exit
            case 3 -> System.exit(0);
        }
    }

    private static void handleEntered(int keycode) {
        double amount;
        switch (keycode) {
            // Withdraw
            case 1 -> {
                amount = Menu.amountInput("Withdraw");
                currentAccount.setAccountOperations(num_operations, false, amount);
                num_operations++;
            }
            // Deposit
            case 2 -> {
                amount = Menu.amountInput("Deposit");
                currentAccount.setAccountOperations(num_operations, true, amount);
                num_operations++;
            }
            // Log Out
            case 3 -> {
                currentAccount = null;
                Menu.showForm();
            }
        }
    }

    private static void finalOutput() {
        if (num_operations == 3) {
            Transaction[] transactions = new Transaction[3];
            // Set Values for all transactions in array
            ExecutorService es = Executors.newFixedThreadPool(3);
            for (int i = 0; i < transactions.length; i++) {
                transactions[i] = new Transaction(currentAccount, i);
                es.submit(transactions[i]);
            }
            es.shutdown();
        }
    }
    public static void initialize() {
        Account account = new Account(1, "Carl Nicolas", "Mendoza", "carl.nicolas.v.mendoza@gmail.com", "Cnnmcn54$", 0);
        accountList.add(account);
    }
}
