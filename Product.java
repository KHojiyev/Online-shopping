package module3.Extra.OnlineShopping;

public class Product {
    private String name;
    private String sort;
    private boolean newOne;
    private Category category;
    private double price;
    private double amount;

    public Product(String name, String sort, boolean newOne, Category category, double price, double amount) {
        this.name = name;
        this.sort = sort;
        this.newOne = newOne;
        this.category = category;
        this.price = price;
        this.amount = amount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewOne() {
        return newOne;
    }

    public void setNewOne(boolean newOne) {
        this.newOne = newOne;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
