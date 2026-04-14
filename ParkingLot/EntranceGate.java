import java.time.LocalDateTime;
import java.util.UUID;

public class EntranceGate {
    private ParkingLot parkingLot;

    public EntranceGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        ParkingSpot spot = parkingLot.findSpot();
        if(spot == null) {
            System.out.println("No spot available!");
            return null;
        }

        spot.setEmpty(false);
        parkingLot.updateSpot(spot.getId(), false);
        String ticketId = UUID.randomUUID().toString();
        return new Ticket(ticketId, LocalDateTime.now(), spot, vehicle);
    }

    public ParkingLot getParkingLot() { return parkingLot; }
    public void setParkingLot(ParkingLot parkingLot) { this.parkingLot = parkingLot; }
}
