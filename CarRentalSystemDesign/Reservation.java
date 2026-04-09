import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private String userId;
    private int vehicleId;
    private final LocalDate dateBookedFrom;
    private final LocalDate dateBookedTo;
    private final ReservationType reservationType;
    private ReservationStatus reservationStatus;


    public Reservation(int reservationId,
                       int vehicleId,
                       String userId,
                       LocalDate dateBookedFrom,
                       LocalDate dateBookedTo,
                       ReservationType reservationType) {

        this.reservationId = reservationId;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.dateBookedFrom = dateBookedFrom;
        this.dateBookedTo = dateBookedTo;
        this.reservationType = reservationType;
        this.reservationStatus = ReservationStatus.SCHEDULED;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDateBookedFrom() {
        return dateBookedFrom;
    }

    public LocalDate getDateBookedTo() {
        return dateBookedTo;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}