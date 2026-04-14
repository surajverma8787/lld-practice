import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private HashMap<Integer, ParkingSpot> spots;
    private ReentrantLock lock = new ReentrantLock();

    public ParkingLot(int totalSpots) {
        spots = new HashMap<>();
        for (int i = 1; i <= totalSpots; i++) {
            spots.put(i, new ParkingSpot(i));
        }
    }

    public ParkingSpot findSpot() {
        lock.lock();
        try {
            for (ParkingSpot spot : spots.values()) {
                if (spot.isEmpty()) {
                    spot.setEmpty(false); // Mark immediately inside lock
                    return spot;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void updateSpot(int spotId, boolean isEmpty) {
        lock.lock();
        try {
            if (spots.containsKey(spotId)) {
                spots.get(spotId).setEmpty(isEmpty);
            }
        } finally {
            lock.unlock();
        }
    }

    public HashMap<Integer, ParkingSpot> getSpots() { return spots; }
    public void setSpots(HashMap<Integer, ParkingSpot> spots) { this.spots = spots; }
}
