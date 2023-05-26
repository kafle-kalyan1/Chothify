package model;

public class Cart {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;
    private String name;
    private String image_url;
    private double price;

    public Cart(int id, int userId, int productId, int quantity, double totalPrice, String name, String image_url, double price) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.name = name;
        this.image_url = image_url;
        this.price =  price;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image_url;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
}
