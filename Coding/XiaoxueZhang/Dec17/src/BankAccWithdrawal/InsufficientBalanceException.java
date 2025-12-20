package BankAccWithdrawal;
//- Create custom exception `InsufficientBalanceException` extending `Exception`:
//  - `private double balance`
//  - `private double requestedAmount`
//  - Constructor accepting both values
//  - Override `getMessage()` to return formatted message

public class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;

    public InsufficientBalanceException(double balance,double requestedAmount){
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public String getMessage() {
        return "Insufficient balance. Current balance: " + balance +
                ", Requested amount: " + requestedAmount;
    }

}
