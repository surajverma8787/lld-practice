import java.util.*;

public class VehicleRentalSystem {

    List<Store> storeList;
    List<User> userList;

    public VehicleRentalSystem(){
        storeList = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public Store getStore(int storeId) {
        return storeList.stream().filter(store -> store.getStoreId() == storeId).findFirst().get();
    }

    public User getUser(String userId) {
        return userList.stream()
                .filter(u -> u.getUserID().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addStore(Store store) {
        storeList.add(store);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeStore(int storeId) {
        storeList.removeIf(store -> store.getStoreId() == storeId);
    }

    public void removeUser(String userId) {
        userList.removeIf(user -> user.getUserID() == userId);
    }
}
