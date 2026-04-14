import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String cartId;
    private User user;
    private Map<String, Integer> productVsQty = new HashMap<>();

    public Cart(String cartId, User user) {
        this.cartId = cartId;
        this.user = user;
    }

    public void addProduct(String productId, int qty) {
        productVsQty.put(productId,
                productVsQty.getOrDefault(productId, 0) + qty);
    }

    public void removeProduct(String productId) {
        productVsQty.remove(productId);
    }

    public void updateQuantity(String productId, int qty) {
        if (qty <= 0) removeProduct(productId);
        else productVsQty.put(productId, qty);
    }

    public Map<String, Integer> viewCart() {
        return productVsQty;
    }

    public void clearCart() {
        productVsQty.clear();
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Integer> getProductVsQty() {
        return productVsQty;
    }

    public void setProductVsQty(Map<String, Integer> productVsQty) {
        this.productVsQty = productVsQty;
    }
}