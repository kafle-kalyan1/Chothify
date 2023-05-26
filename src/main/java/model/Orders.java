package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import other.SqlConnector;

public class Orders {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;
    private Date orderDate;
    private String productName;
    private String productImage;

    
    public Orders(int userId, int productId, int quantity, double totalPrice, Date orderDate) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;

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


    
    public void addOrder(Connection conn) throws SQLException {
        String sql = "INSERT INTO orders (user_id, product_id, quantity, total_price, order_date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, getUserId());
        stmt.setInt(2, getProductId());
        stmt.setInt(3, getQuantity());
        stmt.setDouble(4, getTotalPrice());
        stmt.setTimestamp(5, new Timestamp(getOrderDate().getTime()));
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            setId(rs.getInt(1));
        }
        rs.close();
        stmt.close();
    }
    
	}

        


    