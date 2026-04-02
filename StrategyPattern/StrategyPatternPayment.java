class PaymentProcessor {
    void processPayment(String type, double amount) {
        switch (type) {
            case "credit_card":
                System.out.println("Using Credit Card");
                  break;
            case "net_banking":
                System.out.println("Using net banking");
                  break;
            case "cash":
                System.out.println("Using cash");
                  break;
            default:
                System.out.println("undefined type");
                  break;
        }
    }
}

public class StrategyPatternPayment {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment("credit_card", 20);
    }
}