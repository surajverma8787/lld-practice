import java.util.List;

public class Order {
    private String orderId;
    private User user;
    private List<OrderItem> orderItems;
    private Warehouse warehouse;
    private Address deliveryAddress;
    private Invoice invoice;
    private Payment payment;
    private OrderStatus status;

    public Order(String orderId, User user, List<OrderItem> orderItems,
                 Warehouse warehouse, Address deliveryAddress) {
        this.orderId = orderId;
        this.user = user;
        this.orderItems = orderItems;
        this.warehouse = warehouse;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.IN_PROGRESS;
    }

    public void confirmOrder() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void dispatchOrder() {
        this.status = OrderStatus.DISPATCHED;
    }

    public void deliverOrder() {
        this.status = OrderStatus.DELIVERED;
    }

    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}