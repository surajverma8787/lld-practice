import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    List<Item> itemList;
    HashMap<Integer, Item> itemMap;

    public Inventory() {
        itemList = new ArrayList<>();
        itemMap = new HashMap<>();
    }

    public void addItem(Item item) {
        itemMap.put(item.getCode(), item);
        itemList.add(item);
    }

    public boolean isItemAvailableByCode(int code) {
        return itemMap.get(code).getAvailability();
    }

    public Item getItemByCode(int code) {
        return itemMap.get(code);
    }
}
