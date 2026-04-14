import java.time.LocalDateTime;

public class ExitGate {
    private ParkingLot parkingLot;
    private PricingStrategy pricingStrategy;
    private PaymentService paymentService;

    public ExitGate(ParkingLot parkingLot, PricingStrategy pricingStrategy, PaymentService paymentService) {
        this.parkingLot = parkingLot;
        this.pricingStrategy = pricingStrategy;
        this.paymentService = paymentService;
    }

    public boolean processExit(Ticket ticket) {
        LocalDateTime exitTime = LocalDateTime.now();
        double amount = pricingStrategy.calculatePrice(ticket.getEntryTime(), exitTime);

        boolean paymentSuccess = paymentService.processPayment(amount);
        if (paymentSuccess) {
            parkingLot.updateSpot(ticket.getParkingSpot().getId(), true);
            System.out.println("Payment of " + amount + " successful. Spot freed!");
        }

        return paymentSuccess;
    }

    public ParkingLot getParkingLot() { return parkingLot; }
    public void setParkingLot(ParkingLot parkingLot) { this.parkingLot = parkingLot; }

    public PricingStrategy getPricingStrategy() { return pricingStrategy; }
    public void setPricingStrategy(PricingStrategy pricingStrategy) { this.pricingStrategy = pricingStrategy; }

    public PaymentService getPaymentService() { return paymentService; }
    public void setPaymentService(PaymentService paymentService) { this.paymentService = paymentService; }
}
