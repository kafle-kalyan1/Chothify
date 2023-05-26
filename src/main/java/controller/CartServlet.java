package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartDao;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("Uid") == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }

        Object userObj = session.getAttribute("Uid");
        int userId = Integer.parseInt(userObj.toString());
        
        // Get cart items for the user
        List<Cart> cartItems = null;
        try {
            cartItems = CartDao.getCartItemsByUserId(userId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Cann't Show cart right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
        }
        
        // Set the cart items in the request attributes and forward to the cart page
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("View/User/Cart.jsp").forward(request, response);
    }
}
