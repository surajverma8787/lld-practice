import java.util.*

public class ReservationManager {

    private final VehicleInventoryManager inventory;
    private final ReservationRepository reservationRepository;
    private final AtomicInteger reservationIdGenerator = new AtomicInteger(20000);

    public ReservationManager(VehicleInventoryManager inventory) {
        this.inventory = inventory;
        this.reservationRepository = new ReservationRepository();
        this.inventory.setReservationRepository(this.reservationRepository);
    }

    public Reservation createReservation(int vehicleId, User user,
            LocalDate from,
            LocalDate to,
            ReservationType type
) throws Exception {
     int reservationId = reservationIdGenerator.getAndIncrement();
        boolean reserved = inventory.reserve(vehicleId, reservationId, from, to);

        if (!reserved) {
            throw new RuntimeException("Vehicle not available for selected dates");
        }

        Reservation reservation = new Reservation(reservationId, vehicleId,
                user.getUserId(), from, to, type);

        reservationRepository.save(reservation);
        return reservation;
    }

    public void cancelReservation(int reservationId) throws Exception {
        Reservation reservation = reservationRepository.getReservationById(reservationId);

        if(!reservation.isPresent()) {
            throw new RuntimeException("Reservation not found");
        }

        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        inventory.release(
                reservation.getVehicleId(),
                reservation.getReservationId());

        reservationRepository.remove(reservationId);
    }

    public void startTrip(int reservationId) {
        Reservation r = reservationRepository.getReservationById(reservationId)
        
        if(!reservation.isPresent()) {
            throw new RuntimeException("Reservation not found");
        }

        reservation.setReservationStatus(ReservationStatus.IN_USE);
    }

     public void submitVehicle(int reservationId) {

        Reservation r = reservationRepository.getReservationById(reservationId)
        
        if(!reservation.isPresent()) {
            throw new RuntimeException("Reservation not found");
        }

        reservation.setReservationStatus(ReservationStatus.COMPLETED);

        inventory.release(
            reservation.getVehicleId(),
            reservation.getReservationId()
        );
    }

    public void remove(int reservationId) {
        reservationRepository.remove(reservationId);
    }
}