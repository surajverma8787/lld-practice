import java.util.ArrayList;
import java.util.List;

public class WarehouseController {
    private List<Warehouse> warehouses = new ArrayList<>();

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(String warehouseId) {
        warehouses.removeIf(w -> w.getWarehouseId().equals(warehouseId));
    }

    public Warehouse findNearestWarehouse(Address address) {
        return warehouses.isEmpty() ? null : warehouses.get(0);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}