// Without Factory Pattern

// interface Payment {
//     void pay();
// }

// class UpiPayment implements Payment {
//     public void pay() {
//         System.out.println("Paying via UPI");
//     }
// }

// class CreditCardPayment implements Payment {
//     public void pay() {
//         System.out.println("Paying via Credit Card");
//     }
// }

// public class FactoryPattern {
//     public static void main(String[] args) {

//         String type = "UPI";
//         Payment payment;

//         if (type.equals("UPI")) {
//             payment = new UpiPayment();
//         } else if (type.equals("CARD")) {
//             payment = new CreditCardPayment();
//         } else {
//             throw new RuntimeException("Invalid type");
//         }

//         payment.pay();
//     }
// }

// With Factory Pattern

// WITH FACTORY PATTERN

interface Payment {
    void pay();
}

class UpiPayment implements Payment {
    public void pay() {
        System.out.println("Paying via UPI");
    }
}

class CreditCardPayment implements Payment {
    public void pay() {
        System.out.println("Paying via Credit Card");
    }
}

// Factory class
class PaymentFactory {

    public static Payment getPayment(String type) {

        if (type.equalsIgnoreCase("UPI")) {
            return new UpiPayment();
        } else if (type.equalsIgnoreCase("CARD")) {
            return new CreditCardPayment();
        }

        throw new RuntimeException("Invalid payment type");
    }
}

public class FactoryPattern {

    public static void main(String[] args) {

        String type = "UPI"; // try changing to "CARD"

        // Client does NOT create objects directly
        Payment payment = PaymentFactory.getPayment(type);

        payment.pay();
    }
}