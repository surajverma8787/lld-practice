import java.time.LocalDateTime;
import java.util.List;

public class Invoice {
    private String invoiceId;
    private String orderId;
    private String userId;
    private List<OrderItem> orderItems;
    private double subTotal;
    private double tax;
    private double totalAmount;
    private String paymentId;
    private LocalDateTime generatedAt;

    public Invoice(String invoiceId, String orderId, String userId,
                   List<OrderItem> orderItems, String paymentId) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.userId = userId;
        this.orderItems = orderItems;
        this.paymentId = paymentId;
        this.generatedAt = LocalDateTime.now();
        calculateAmounts();
    }

    private void calculateAmounts() {
        for(OrderItem order: orderItems) {
            this.subTotal += order.getTotalPrice();
        }
        this.tax = subTotal * 0.18;
        this.totalAmount = subTotal + tax;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}