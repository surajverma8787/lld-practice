import java.util.*;

public class InventoryApp {

    public static void main(String[] args) {

        // ✅ Step 1: Create Categories
        ProductCategory electronics = new ProductCategory("C1", "Electronics");

        // ✅ Step 2: Create Products
        Product laptop = new Product("P1", "Laptop", electronics);
        Product phone = new Product("P2", "Phone", electronics);

        // ✅ Step 3: Create Warehouse
        Address warehouseAddress = new Address("Street 1", "Bangalore", "KA", "560001");
        Warehouse warehouse = new Warehouse("W1", "Main Warehouse", warehouseAddress);

        // Add inventory
        warehouse.getInventory().addStock("P1", 10);
        warehouse.getInventory().addStock("P2", 20);

        // ✅ Step 4: Create User
        Address userAddress = new Address("Street 2", "Bangalore", "KA", "560002");
        User user = new User("U1", "Suraj", userAddress);

        // ✅ Step 5: Create Cart
        Cart cart = new Cart("CART1", user);
        cart.addProduct("P1", 1);
        cart.addProduct("P2", 2);

        System.out.println("Cart Items: " + cart.viewCart());

        // ✅ Step 6: Convert Cart → OrderItems
        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cart.getProductVsQty().entrySet()) {
            String productId = entry.getKey();
            int qty = entry.getValue();

            Product product = productId.equals("P1") ? laptop : phone;

            // assume price
            double price = productId.equals("P1") ? 50000 : 20000;

            orderItems.add(new OrderItem(product, qty, price));
        }

        // ✅ Step 7: Create Order
        Order order = new Order("O1", user, orderItems, warehouse, userAddress);

        // ✅ Step 8: Check Inventory & Deduct Stock
        for (OrderItem item : orderItems) {
            String productId = item.getProduct().getProductId();
            int qty = item.getQuantity();

            if (!warehouse.getInventory().checkAvailability(productId, qty)) {
                throw new RuntimeException("Product out of stock: " + productId);
            }
            warehouse.getInventory().updateStock(productId, qty);
        }

        order.confirmOrder();
        System.out.println("Order Confirmed");

        // ✅ Step 9: Calculate total amount
        double totalAmount = orderItems.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();

        // ✅ Step 10: Payment
        Payment payment = new Payment("PAY1", order.getOrderId(), totalAmount, PaymentMethod.UPI);
        payment.markSuccess();
        order.setPayment(payment);

        System.out.println("Payment Status: " + payment.getStatus());

        // ✅ Step 11: Generate Invoice
        Invoice invoice = new Invoice(
                "INV1",
                order.getOrderId(),
                user.getUserId(),
                orderItems,
                payment.getPaymentId()
        );

        order.setInvoice(invoice);

        // ✅ Step 12: Deliver Order
        order.dispatchOrder();
        order.deliverOrder();

        // ✅ Final Output
        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("User: " + user.getName());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Total Amount: " + invoice.getTotalAmount());
        System.out.println("Payment: " + payment.getStatus());
    }
}