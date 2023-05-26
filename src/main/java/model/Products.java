package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.JasperException;

import other.SqlConnector;

public class Products {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String brand;
    private String imageUrl;
    private double rating;

    public Products() {
        // empty constructor
    }

    public Products(int id, String name, String category, double price, int quantity, String brand, String imageUrl, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public double getRating() {
        return rating;
    }

    public void SetRating( double rating) {
        this.rating = rating;
    }

    // Method to retrieve all products from the database
    public static List<Products> getAllProducts() throws SQLException, ClassNotFoundException, JasperException {
        List<Products> productsList = new ArrayList<>();
        	
        // Get database connection
        Connection con = SqlConnector.getCon();

        // Execute SQL query to select all products
        String query = "SELECT * FROM products";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        // Loop through the result set and create a Product object for each row
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String brand = rs.getString("brand");
            String imageUrl = rs.getString("image_url");
            double rating = rs.getDouble("rating");

            Products product = new Products(id, name, category, price, quantity, brand, imageUrl, rating);
            productsList.add(product);
        }

        return productsList;
        
        
    }
    public static Products getProductById(int id) throws SQLException, ClassNotFoundException {
        Products product = null;

        // Get database connection
        Connection con = SqlConnector.getCon();

        // Execute SQL query to select product with given id
        String query = "SELECT * FROM products WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        // If result set has a row, create a Product object with the data
        if (rs.next()) {
            String name = rs.getString("name");
            String category = rs.getString("category");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String brand = rs.getString("brand");
            String imageUrl = rs.getString("image_url");
            double rating = rs.getDouble("rating");

            product = new Products(id, name, category, price, quantity, brand, imageUrl, rating);
        }

        // Close database resources
        rs.close();
        ps.close();
        con.close();

        return product;
    }
    public static boolean deleteProductById(int id) throws SQLException, ClassNotFoundException {
        // Get database connection
        Connection con = SqlConnector.getCon();

        // Execute SQL query to delete product with given id
        String query = "DELETE FROM products WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();

        // Close database resources
        ps.close();
        con.close();

        // Return true if a row was affected, indicating a product was deleted
        return (rowsAffected > 0);
    }
   


  
}