package module3.Extra.OnlineShopping;

public class ShoppingBox {
    private String id;
    private Product product;
    private int productAmount;
    private double totalPrice;

    public ShoppingBox(String id, Product product, int productAmount, double totalPrice) {
        this.id = id;
        this.product = product;
        this.productAmount = productAmount;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
