package ca.centennialcollege;

public class Transaction implements Runnable {
    private Account account;
    private int currentOperation;
    public Transaction(Account account, int currentOperation) {
        this.account = account;
        this.currentOperation = currentOperation;
    }

    @Override
    public void run() {
        AccountOperation currentAccountOperation = account.getAccountOperation(currentOperation);
        boolean isDeposit = currentAccountOperation.getIsDeposit();
        double amount = currentAccountOperation.getAmount();
        if (isDeposit == false) {
            account.withdraw(amount);
        } else {
            account.deposit(amount);
        }
        System.out.println("\nExecuting transaction #" + (currentOperation + 1) +
                "\nOperation: " + (isDeposit ? "Deposit" : "Withdraw") +
                "\nAmount: " + amount +
                "\nCurrent Output: " + account);
    }
}
