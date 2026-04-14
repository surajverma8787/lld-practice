import java.util.HashMap;

public class ParkingLot {
    private HashMap<Integer, ParkingSpot> spots;

    public ParkingLot(int totalSpots) {
        spots = new HashMap<>();
        for (int i = 1; i <= totalSpots; i++) {
            spots.put(i, new ParkingSpot(i));
        }
    }

    public ParkingSpot findSpot() {
        for (ParkingSpot spot : spots.values()) {
            if (spot.isEmpty()) {
                return spot;
            }
        }
        return null; // No spot available
    }

    public void updateSpot(int spotId, boolean isEmpty) {
        if (spots.containsKey(spotId)) {
            spots.get(spotId).setEmpty(isEmpty);
        }
    }

    public HashMap<Integer, ParkingSpot> getSpots() { return spots; }
    public void setSpots(HashMap<Integer, ParkingSpot> spots) { this.spots = spots; }
}
