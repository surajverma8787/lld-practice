import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BillManager {

    private BillingStrategy billingStrategy;

    private final Map<Integer, Bill> bills = new ConcurrentHashMap<>();

    public BillManager(BillingStrategy billingStrategy) {
        this.billingStrategy = billingStrategy;
    }

    public Bill generateBill(Reservation reservation) {

        Bill bill = billingStrategy.generateBill(reservation);
        bills.put(bill.getBillId(), bill);
        return bill;
    }

    public Optional<Bill> getBill(int billId) {
        return Optional.ofNullable(bills.get(billId));
    }

    public void setBillingStrategy(BillingStrategy billingStrategy) {
        this.billingStrategy = billingStrategy;
    }
}
