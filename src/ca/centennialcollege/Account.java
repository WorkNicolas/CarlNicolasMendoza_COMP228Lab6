package ca.centennialcollege;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private int account_id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private double balance;
    private AccountOperation[] accountOperations = new AccountOperation[3];
    private static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);

    public Account(int account_id, String fname, String lname, String email, String password, double balance) {
        this.account_id = account_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void withdraw(double amount) {
        Menu.clearScreen();
        if (amount <= balance) {
            System.out.println("Withdrawing " + currencyFormatter.format(amount) + " from your account.");
            balance -= amount;
        } else {
            System.out.println("Insufficient balance. Unable to withdraw " + currencyFormatter.format(amount) + ".");
        }
    }

    public void deposit(double amount) {
        Menu.clearScreen();
        System.out.println("Depositing " + currencyFormatter.format(amount) + " to your account.");
        balance += amount;
    }

    public void setAccountOperations(int num_operation, boolean isDeposit, double amount) {
        accountOperations[num_operation] = new AccountOperation(isDeposit, amount);
    }

    public AccountOperation getAccountOperation(int num_operation) {
        return accountOperations[num_operation];
    }

    @Override
    public String toString() {
        return account_id + " - " + fname + " " + lname + "\nBalance: " + currencyFormatter.format(balance);
    }
}
