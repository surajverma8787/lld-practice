public class Item {
    int code;
    int price;
    boolean isAvailable;

    Item(int code, int price) {
        this.code = code;
        this.price = price;
        this.isAvailable = true;
    }

    public int getCode() {
        return this.code;
    }

    public int getItemPrice() {
        return this.price;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
