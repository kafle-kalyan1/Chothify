package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDao;

@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();

        if (session.getAttribute("Uid") == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            CartDao.removeCartItem(productId);
        } catch (ClassNotFoundException | SQLException e) {
        	request.setAttribute("errorMessage", "Can't remove at this time...");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        response.sendRedirect("CartServlet");
    }
}
