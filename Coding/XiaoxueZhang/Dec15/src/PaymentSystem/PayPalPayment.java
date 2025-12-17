package PaymentSystem;

public class PayPalPayment implements PaymentMethod {
    private final String paymentType="PayPal";
    private double amount;

    @Override
    public boolean processPayment(double amount) {
        System.out.println("payment type: "+paymentType+", amount: "+amount);
        return true;
    }
}
