package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import other.SqlConnector;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setImage(rs.getString("image"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public User getCurrentUser(int userId) throws SQLException, ClassNotFoundException {
        User user = null;

        // Get database connection
        Connection con = SqlConnector.getCon();

        // Execute SQL query to select user with given id
        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        // If result set has a row, create a User object with the data
        if (rs.next()) {
            String fullName = rs.getString("fullName");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String image = rs.getString("image");
            String password = rs.getString("password");
            Boolean isAdmin = rs.getBoolean("isAdmin");

            user = new User();
            user.setId(userId);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhone(phone);
            user.setImage(image);
            user.setPassword(password);
            user.setAdmin(isAdmin);
        }

        // Close database resources
        rs.close();
        ps.close();
        con.close();

        return user;
    }
    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users SET fullName=?, email=?, address=?, phone=?, image=? WHERE id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {
            conn = SqlConnector.getCon();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getImage());
            stmt.setInt(6, user.getId());
            
            int numRowsUpdated = stmt.executeUpdate();
            success = (numRowsUpdated == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
        	 conn.close();
        }
        
        return success;
    }


}
