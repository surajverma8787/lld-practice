public class Warehouse {
    private String warehouseId;
    private String name;
    private Inventory inventory;
    private Address address;

    public Warehouse(String warehouseId, String name, Address address) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
        this.inventory = new Inventory(warehouseId + "_INV", this);
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}