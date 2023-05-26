package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import other.SqlConnector;

public class CartDao {
	private static final String SELECT_CART_ITEMS_BY_USER_ID = "SELECT c.id, c.user_id, c.product_id, c.quantity, c.total_price, p.name,  p.image_url, p.price FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";

	public static List<Cart> getCartItemsByUserId(int userId) throws SQLException, ClassNotFoundException {
	    List<Cart> cartItems = new ArrayList<>();
	    
	    try (Connection conn = SqlConnector.getCon();
	            PreparedStatement stmt = conn.prepareStatement(SELECT_CART_ITEMS_BY_USER_ID)) {
	        stmt.setInt(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int productId = rs.getInt("product_id");
	                int quantity = rs.getInt("quantity");
	                double totalPrice = rs.getDouble("total_price");
	                String name = rs.getString("name");
	                String image_url = rs.getString("image_url");
	                double price = rs.getDouble("price");	
	                cartItems.add(new Cart(id, userId, productId, quantity, totalPrice, name, image_url, price));
	            }
	        }
	    }
	    return cartItems;
	}
	public static boolean removeCartItem(int cartItemId) throws SQLException, ClassNotFoundException {
		System.out.println(cartItemId);
	    String sql = "DELETE FROM cart WHERE id = ?";
	    
	    try (Connection conn = SqlConnector.getCon();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, cartItemId);
	        int rowsAffected = stmt.executeUpdate();
	        conn.close();
	        return rowsAffected > 0;
	    }
	  
	}


}
