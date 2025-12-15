public class BankAccount {
    private String accountNumber;
    private double balance;
    public BankAccount (String accountNumber,double balance){
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        if(balance<0){
            System.out.println("Invalid balance");
            return;
        }
        this.balance = balance;
    }
    public void deposit(double amount){
        if(amount<0){
            System.out.println("Invalid amount");
            return;
        }
        this.balance+=amount;
    }
    public void withdraw(double amount){
        if(amount<0||this.balance<amount){
            System.out.println("Invalid amount");
            return;
        }
        this.balance-=amount;
    }

}

