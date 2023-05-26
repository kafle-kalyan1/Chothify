package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import other.SqlConnector;

@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

        if (session.getAttribute("Uid") == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        double price = Double.parseDouble(request.getParameter("price"));
        double total_price = quantity * price;
        try {
            // Insert cart item into cart table
            Connection conn = SqlConnector.getCon();
            String query = "INSERT INTO cart (user_id, product_id, quantity, total_price)\r\n"
            		+ "VALUES (?, ?, ?, ?)\r\n"
            		+ "ON DUPLICATE KEY UPDATE\r\n"
            		+ "  quantity = quantity + VALUES(quantity),\r\n"
            		+ "  total_price = total_price + VALUES(total_price)\r\n"
            		+ "";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.setFloat(4, (float) total_price);
            statement.executeUpdate();
            conn.close();

            response.sendRedirect("View/User/Home.jsp");
        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println(e);
        	 request.setAttribute("errorMessage", "Cann't add product right now");
             request.getRequestDispatcher("View/error.jsp").forward(request, response);
             
             return;
        }
    }
}
