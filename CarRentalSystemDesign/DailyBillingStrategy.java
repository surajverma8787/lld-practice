import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DailyBillingStrategy implements BillingStrategy {

    VehicleInventoryManager vehicleInventoryManager;

    private final AtomicInteger billIdGenerator = new AtomicInteger(5000);

    public DailyBillingStrategy(VehicleInventoryManager vehicleInventoryManager) {
        this.vehicleInventoryManager = vehicleInventoryManager;
    }

    @Override
    public Bill generateBill(Reservation reservation) {

        long days = ChronoUnit.DAYS.between(
                reservation.getDateBookedFrom(),
                reservation.getDateBookedTo()
        ) + 1;

        Vehicle vehicle = vehicleInventoryManager.getVehicleById(reservation.getVehicleId());
        double rate = vehicle.getDailyRentalCost();

        double total = days * rate;

        return new Bill(
                billIdGenerator.getAndIncrement(),
                reservation.getReservationId(),
                total
        );
    }
}
