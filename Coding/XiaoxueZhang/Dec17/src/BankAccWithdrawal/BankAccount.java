package BankAccWithdrawal;
//- Create `BankAccount` class:
//  - `private String accountId`
//  - `private double balance`
//  - Constructor, getters
//  - `public void withdraw(double amount) throws BankAccWithdrawal.InsufficientBalanceException`
//    - Check if amount > balance, throw exception if true
//    - Otherwise deduct amount and print success message
public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException{
        if(balance<amount){
            throw new InsufficientBalanceException(balance,amount);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }
}
