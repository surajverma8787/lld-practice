import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class VehicleInventoryManager {
     // vehicleId → Vehicle
    private final ConcurrentMap<Integer, Vehicle> vehicles = new ConcurrentHashMap<>();

    // vehicleId → list of reservation IDs (metadata index)
    private final ConcurrentMap<Integer, List<Integer>> vehicleBookingIds = new ConcurrentHashMap<>();

    private final ConcurrentMap<Integer, ReentrantLock> vehicleLocks = new ConcurrentHashMap<>();

    private ReservationRepository reservationRepository;

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleID(), vehicle);
    }

    public Vehicle getVehicleById(int vehicleId) {
        return vehicles.get(vehicleId);
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    private ReentrantLock lockForVehicle(int vehicleId) {
        vehicleLocks.putIfAbsent(vehicleId, new ReentrantLock());
        return vehicleLocks.get(vehicleId);
    }

     public boolean isAvailable(int vehicleId, LocalDate from, LocalDate to) {
        Vehicle vehicle = vehicles.get(vehicleId);

        if (vehicle == null) return false;
        if (vehicle.getVehicleStatus() == VehicleStatus.MAINTENANCE) return false;

        List<Integer> reservationIDs = vehicleBookingIds.get(vehicleId);
        if(reservationIDs == null || reservationIDs.isEmpty()) {
            return true;
        }

        for (int reservationID : reservationIDs) {
             Reservation reservation = reservationRepository.findById(reservationID).get();

            if (reservation == null) continue;

            if (reservation.getReservationStatus() == ReservationStatus.CANCELLED ||
                    reservation.getReservationStatus() == ReservationStatus.COMPLETED) {
                continue;
            }

            LocalDate bookedFrom = reservation.getDateBookedFrom();
            LocalDate bookedTill = reservation.getDateBookedTo();

            boolean isOverlap = !(bookedTill.isBefore(from) || bookedFrom.isAfter(to));

            if(isOverlap)   return false;
        }

        return true;
     }

      public boolean reserve(int vehicleId, int reservationId, LocalDate from, LocalDate to) {
          ReentrantLock lock = lockForVehicle(vehicleId);
          lock.lock();

           try {
            if (!isAvailable(vehicleId, from, to)) {
                return false;
            }

            vehicleBookingIds.putIfAbsent(vehicleId, new ArrayList<>());
            vehicleBookingIds.get(vehicleId).add(reservationId);

            return true;
        } finally {
            lock.unlock();
        }
      }

      public void release(int vehicleId, int reservationId) {
         ReentrantLock lock = lockForVehicle(vehicleId);
         lock.lock();

        try {
            List<Integer> reservationIds = vehicleBookingIds.get(vehicleId);

            if (reservationIds != null) {
                reservationIds.remove(Integer.valueOf(reservationId));
            }

            // Optional: clean up empty list
            if (reservationIds != null && reservationIds.isEmpty()) {
                vehicleBookingIds.remove(vehicleId);
            }

        } finally {
            lock.unlock();
        }
      }

      public List<Vehicle> getAvailableVehicles(
        VehicleType type,
        LocalDate from,
        LocalDate to
    ) {
        return vehicles.values()
                .stream()
                .filter(v -> v.getVehicleType() == type)
                .filter(v -> isAvailable(v.getVehicleID(), from, to))
                .collect(Collectors.toList());
    }




}