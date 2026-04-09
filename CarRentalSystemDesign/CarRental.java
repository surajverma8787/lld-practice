void main() throws Exception {

    IO.println("\n===== LLD: Car Rental System Demo =====");

    VehicleRentalSystem rentalSystem = new VehicleRentalSystem();

    // Create a store1
    Location store1Location = new Location(
            "45 Area1",   // address
            "City1",
            12345,
            "State1"
    );
    Store store1 = new Store(1001, store1Location);
    rentalSystem.addStore(store1);

    // Create 2 users
    User user1 = new User("801", "SJ", "DL2022GDG556690");
    User user2 = new User("802", "DJ", "DL2017DHW9090765231");
    rentalSystem.addUser(user1);
    rentalSystem.addUser(user2);

    Vehicle v1 = new Vehicle(1, "DL1234", VehicleType.FOUR_WHEELER);
    v1.setDailyRentalCost(1100);

    Vehicle v2 = new Vehicle(2, "DL5678", VehicleType.FOUR_WHEELER);
    v2.setDailyRentalCost(1400);

    store1.getInventory().addVehicle(v1);
    store1.getInventory().addVehicle(v2);

    Store selectedStore = rentalSystem.getStore(1001);

    LocalDate fromDate = LocalDate.of(2025, 12, 5);
    LocalDate toDate = LocalDate.of(2025, 12, 7);

    IO.println("\nAvailable vehicles from " + fromDate + " to " + toDate + ":");

    for (Vehicle v : selectedStore.getVehicles(VehicleType.FOUR_WHEELER, fromDate, toDate)) {
        IO.println(" - " + v.getVehicleID() + ": " + v.getVehicleType());
    }

    IO.println("\nCreating reservation...");

    Reservation reservation =
            selectedStore.createReservation(
                    1,                // vehicle ID
                    user1,
                    fromDate,
                    toDate,
                    ReservationType.DAILY
            );

    IO.println("Reservation created with ID: " + reservation.getReservationId());


    IO.println("\nStarting trip...");
    selectedStore.startTrip(reservation.getReservationId());


    IO.println("\nGenerating bill...");
    Bill bill = selectedStore.generateBill(reservation.getReservationId(),
            new DailyBillingStrategy(selectedStore.getInventory()));

    IO.println("Bill ID: " + bill.getBillId());
    IO.println("Bill Amount: " + bill.getTotalBillAmount());

    IO.println("\nProcessing Payment...");

    Payment payment = selectedStore.makePayment(bill, new UPIPaymentStrategy(), bill.getTotalBillAmount());

    IO.println("\n===== PAYMENT RECEIPT =====");
    IO.println("Payment ID: " + payment.getPaymentId());
    IO.println("Paid Amount: " + payment.getAmountPaid());
    IO.println("Payment Mode: " + payment.getPaymentMode());
    IO.println("Payment Date: " + payment.getPaymentDate());
    IO.println("============================");
}