public class ParkingSpot {
    private int id;
    private boolean isEmpty;

    public ParkingSpot(int id) {
        this.id = id;
        this.isEmpty = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isEmpty() { return isEmpty; }
    public void setEmpty(boolean empty) { this.isEmpty = empty; }
}
