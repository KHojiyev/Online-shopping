package module3.Extra.OnlineShopping.backup;

public class Order {
    private String id;
    private User user;
    private Product product;
    private int productAmount;
    private double totalPrice;
    private boolean orderStatus;

    public Order(String id, User user, Product product, int productAmount, double totalPrice, boolean orderStatus) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.productAmount = productAmount;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}
