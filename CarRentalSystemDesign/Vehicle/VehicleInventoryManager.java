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
             Reservation res = reservationRepository.getReservationById(resId);

            if (res == null) continue;

            if (res.getReservationStatus() == ReservationStatus.CANCELLED ||
                res.getReservationStatus() == ReservationStatus.COMPLETED) {
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




}