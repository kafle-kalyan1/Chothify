package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Orders;
import other.SqlConnector;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

        if (session.getAttribute("Uid") == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }
        Object userObj = session.getAttribute("Uid");
        int userId = Integer.parseInt(userObj.toString());
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Date orderDate = new Date();
        
        try {
            Connection conn = SqlConnector.getCon();
            Orders order = new Orders(userId, productId, quantity, totalPrice, orderDate);
            order.addOrder(conn);
            response.sendRedirect(request.getContextPath() + "/RemoveFromCartServlet?id="+cartId);
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Error retrieving user"+e);
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
           
        }
    }
}
