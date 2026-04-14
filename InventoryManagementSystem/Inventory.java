import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private String inventoryId;
    private Warehouse warehouse;
    private Map<String, Integer> productVsQty = new HashMap<>();

    public Inventory(String inventoryId, Warehouse warehouse) {
        this.inventoryId = inventoryId;
        this.warehouse = warehouse;
    }

    public boolean checkAvailability(String productId, int qty) {
        return productVsQty.getOrDefault(productId, 0) >= qty;
    }

    public void addStock(String productId, int qty) {
        productVsQty.put(productId,
                productVsQty.getOrDefault(productId, 0) + qty);
    }

    public void updateStock(String productId, int qty) {
        if (checkAvailability(productId, qty)) {
            productVsQty.put(productId,
                    productVsQty.get(productId) - qty);
        } else {
            throw new RuntimeException("Insufficient stock");
        }
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Map<String, Integer> getProductVsQty() {
        return productVsQty;
    }

    public void setProductVsQty(Map<String, Integer> productVsQty) {
        this.productVsQty = productVsQty;
    }
}
