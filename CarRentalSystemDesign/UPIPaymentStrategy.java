import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;

public class UPIPaymentStrategy implements PaymentStrategy {

    private final AtomicInteger paymentIdGenerator = new AtomicInteger(9000);

    @Override
    public Payment processPayment(Bill bill, double paymentAmount) {

        Payment payment = new Payment(paymentIdGenerator.getAndIncrement(),
                bill.getBillId(), paymentAmount,
                PaymentMode.UPI, new Date());

        bill.setBillPaid(true);

        return payment;
    }
}
