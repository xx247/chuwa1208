package PaymentSystem;

public class CreditCardPayment implements PaymentMethod{
    private final String paymentType="CreditCard";
    private double amount;

    @Override
    public boolean processPayment(double amount) {
        System.out.println("payment type: "+paymentType+", amount: "+amount);
        return true;
    }

    @Override
    public void printReceipt() {
        System.out.println("credit card receipt");
    }
}
