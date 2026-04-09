public interface PaymentStrategy {
    Payment processPayment(Bill bill, double paymentAmount);
}
