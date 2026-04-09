// Payment Processor - Without Strategy Pattern

// class PaymentProcessor {
//     void processPayment(String type, double amount) {
//         switch (type) {
//             case "credit_card":
//                 System.out.println("Using Credit Card");
//                   break;
//             case "net_banking":
//                 System.out.println("Using net banking");
//                   break;
//             case "cash":
//                 System.out.println("Using cash");
//                   break;
//             default:
//                 System.out.println("undefined type");
//                   break;
//         }
//     }
// }

// public class StrategyPatternPayment {
//     public static void main(String[] args) {
//         PaymentProcessor processor = new PaymentProcessor();
//         processor.processPayment("credit_card", 20);
//     }
// }

interface PaymentStrategy {
    public void processPayment(double amount);
}

class CreditCardPayment1 implements PaymentStrategy {
    String cardNumber;

    CreditCardPayment1(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Paying using credit card");
    }
}

class NetBankingPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paying using net banking");
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paying using cash");
    }
}

class ProcessPayment {
    PaymentStrategy paymentStrategy;

    ProcessPayment(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        paymentStrategy.processPayment(amount);
    }
}

public class StrategyPatternPayment {
    public static void main(String[] args) {
        ProcessPayment process = new ProcessPayment(new CreditCardPayment1("123"));
        process.processPayment(12);
    }
}