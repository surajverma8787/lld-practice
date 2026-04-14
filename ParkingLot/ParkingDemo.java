import java.time.LocalDateTime;

public class ParkingDemo {
    public static void main(String[] args) throws InterruptedException {

        // Step 1: Setup Parking Lot with 10 spots
        ParkingLot parkingLot = new ParkingLot(10);

        // Step 2: Setup Pricing Strategy (Hourly - 50 per hour)
        PricingStrategy pricingStrategy = new HourlyPricing(50.0);

        // Step 3: Setup Payment Service
        PaymentService paymentService = new PaymentService();

        // Step 4: Setup Entrance and Exit Gates
        EntranceGate entranceGate = new EntranceGate(parkingLot);
        ExitGate exitGate = new ExitGate(parkingLot, pricingStrategy, paymentService);

        // ---------------------------------------------------
        // SCENARIO 1: Two Wheeler enters and exits
        // ---------------------------------------------------
        System.out.println("=== SCENARIO 1: Two Wheeler ===");

        Vehicle twoWheeler = new Vehicle("KA01AB1234", VehicleType.TWO_WHEELER);
        Ticket ticket1 = entranceGate.generateTicket(twoWheeler);

        if (ticket1 != null) {
            System.out.println("Ticket Generated!");
            System.out.println("Ticket ID   : " + ticket1.getTicketId());
            System.out.println("Vehicle     : " + ticket1.getVehicle().getVehicleNumber());
            System.out.println("Spot ID     : " + ticket1.getParkingSpot().getId());
            System.out.println("Entry Time  : " + ticket1.getEntryTime());
        }

        // Simulate some time passing (2 seconds for demo)
        Thread.sleep(2000);

        System.out.println("\n--- Vehicle Exiting ---");
        exitGate.processExit(ticket1);

        // ---------------------------------------------------
        // SCENARIO 2: Four Wheeler enters and exits
        // ---------------------------------------------------
        System.out.println("\n=== SCENARIO 2: Four Wheeler ===");

        Vehicle fourWheeler = new Vehicle("KA02XY5678", VehicleType.FOUR_WHEELER);
        Ticket ticket2 = entranceGate.generateTicket(fourWheeler);

        if (ticket2 != null) {
            System.out.println("Ticket Generated!");
            System.out.println("Ticket ID   : " + ticket2.getTicketId());
            System.out.println("Vehicle     : " + ticket2.getVehicle().getVehicleNumber());
            System.out.println("Spot ID     : " + ticket2.getParkingSpot().getId());
            System.out.println("Entry Time  : " + ticket2.getEntryTime());
        }

        Thread.sleep(2000);

        System.out.println("\n--- Vehicle Exiting ---");
        exitGate.processExit(ticket2);

        // ---------------------------------------------------
        // SCENARIO 3: Parking Lot Full
        // ---------------------------------------------------
        System.out.println("\n=== SCENARIO 3: Filling up all spots ===");

        Ticket[] tickets = new Ticket[10];
        for (int i = 0; i < 10; i++) {
            Vehicle v = new Vehicle("KA03ZZ000" + i, VehicleType.FOUR_WHEELER);
            tickets[i] = entranceGate.generateTicket(v);
            if (tickets[i] != null) {
                System.out.println("Vehicle " + v.getVehicleNumber() + " assigned spot: " + tickets[i].getParkingSpot().getId());
            }
        }

        // Try one more vehicle when lot is full
        System.out.println("\n--- Trying to enter when lot is full ---");
        Vehicle extraVehicle = new Vehicle("KA04ZZ9999", VehicleType.TWO_WHEELER);
        Ticket extraTicket = entranceGate.generateTicket(extraVehicle);
        if (extraTicket == null) {
            System.out.println("Entry denied! Parking lot is full.");
        }

        // ---------------------------------------------------
        // SCENARIO 4: Minute based pricing
        // ---------------------------------------------------
        System.out.println("\n=== SCENARIO 4: Minute Based Pricing ===");

        // Free up one spot first
        exitGate.processExit(tickets[0]);

        PricingStrategy minutePricing = new MinutePricing(2.0); // 2 per minute
        ExitGate minuteExitGate = new ExitGate(parkingLot, minutePricing, paymentService);

        Vehicle v2 = new Vehicle("KA05AA1111", VehicleType.TWO_WHEELER);
        Ticket ticket3 = entranceGate.generateTicket(v2);

        if (ticket3 != null) {
            System.out.println("Ticket Generated for: " + ticket3.getVehicle().getVehicleNumber());
            System.out.println("Spot ID: " + ticket3.getParkingSpot().getId());
        }

        Thread.sleep(2000);

        System.out.println("\n--- Vehicle Exiting with Minute Pricing ---");
        minuteExitGate.processExit(ticket3);
    }
}