import java.util.List;
import java.util.ArrayList;

interface StockNotificationObserver {
    void update();
    String getNotificationMethod();
    String getUserId();
}

interface StockAvailabilityObservable {
    void addStockObserver(StockNotificationObserver observer);
    void removeStockObserver(StockNotificationObserver observer);
    void notifyStockObservers();
    boolean purchase(int quantity);
    void restock(int quantity);
}

class IphoneProductObservable implements StockAvailabilityObservable {

    private final String productId;
    private final String productName;
    private final double price;
    private final List<StockNotificationObserver> stockObservers = new ArrayList<>();
    private int stockQuantity;

    public IphoneProductObservable(String productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @Override
    public void addStockObserver(StockNotificationObserver observer) {
        stockObservers.add(observer);
        System.out.println("[+] " + observer.getUserId() + " subscribed for notifications on " + productName);
    }

    @Override
    public void removeStockObserver(StockNotificationObserver observer) {
        stockObservers.remove(observer);
        System.out.println("[-] " + observer.getUserId() + " unsubscribed for notifications on " + productName);
    }

    @Override
    public void notifyStockObservers() {
        if (stockQuantity > 0 && !stockObservers.isEmpty()) {
            System.out.println("Notifying " + stockObservers.size() + " subscribers...");
            for (StockNotificationObserver observer : stockObservers) {
                observer.update();
            }
        }
    }

    @Override
    public void restock(int quantity) {
        boolean wasOutOfStock = (stockQuantity == 0);
        stockQuantity += quantity;
        System.out.println("RESTOCKED: " + productName + " - Added " + quantity + " items | Current stock: " + stockQuantity);

        if (wasOutOfStock && stockQuantity > 0) {
            notifyStockObservers();
        }
    }

    @Override
    public boolean purchase(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
            System.out.println("PURCHASE SUCCESS: " + quantity + " units of " + productName + " | Remaining stock: " + stockQuantity);
            return true;
        } else {
            System.out.println("PURCHASE FAILED: " + productName + " is out of stock! | Available Quantity: " + stockQuantity);
            return false;
        }
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}

class EmailNotificationObserver implements StockNotificationObserver {

    private final String userId;
    private final String emailAddress;

    public EmailNotificationObserver(String userId, String emailAddress) {
        this.userId = userId;
        this.emailAddress = emailAddress;
    }

    @Override
    public void update() {
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("!! EMAIL SENT to: " + emailAddress + " - Product is back in stock! Hurry Up!!");
    }

    @Override
    public String getNotificationMethod() {
        return "Email";
    }

    @Override
    public String getUserId() {
        return userId;
    }
}

class PushNotificationObserver implements StockNotificationObserver {

    private final String userId;
    private final String deviceToken;

    public PushNotificationObserver(String userId, String deviceToken) {
        this.userId = userId;
        this.deviceToken = deviceToken;
    }

    @Override
    public void update() {
        sendPushNotification();
    }

    private void sendPushNotification() {
        System.out.println("!! PUSH NOTIFICATION SENT to: " 
            + deviceToken + " - Product is back in stock! Hurry Up!!");
    }

    @Override
    public String getNotificationMethod() {
        return "Push Notification";
    }

    @Override
    public String getUserId() {
        return userId;
    }
}

public class ObserverPatternECommerce {

    public static void main(String[] args) {

        System.out.println("###### E-commerce Store - Stock Availability Notification Feature Demo ######");

        StockAvailabilityObservable iphoneProduct =
                new IphoneProductObservable("ip15", "iPhone 15", 1250, 10);

        StockNotificationObserver johnPush =
                new PushNotificationObserver("John123", "JohnDeviceP1");

        StockNotificationObserver janeEmail =
                new EmailNotificationObserver("Jane783", "jane783@gmail.com");

        iphoneProduct.purchase(10);
        boolean success = iphoneProduct.purchase(1);

        if (!success) {
            iphoneProduct.addStockObserver(johnPush);
            iphoneProduct.addStockObserver(janeEmail);
        }

        iphoneProduct.restock(20);
    }
}