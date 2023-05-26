package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import other.SqlConnector;

public class ShowOrders {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;
    private Date orderDate;
    private String productName;
    private String productImage;

    public ShowOrders(int userId, int productId, int quantity, double totalPrice, Date orderDate, String productName, String productImage) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.productName = productName;
        this.productImage = productImage;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

  
    public List<ShowOrders> showAllOrders(Connection conn) throws SQLException {
        List<ShowOrders> orderList = new ArrayList<>();
        String sql = "SELECT o.*, p.name, p.image_url FROM orders o JOIN products p ON o.product_id = p.id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            int productId = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");
            double totalPrice = rs.getDouble("total_price");
            Date orderDate = rs.getTimestamp("order_date");
            String productName = rs.getString("name");
            String productImage = rs.getString("image_url");
            ShowOrders order = new ShowOrders(userId, productId, quantity, totalPrice, orderDate, productName, productImage);
            order.setId(id);
            orderList.add(order);
            
        }
        rs.close();
        stmt.close();
        return orderList;
        }

        


    }