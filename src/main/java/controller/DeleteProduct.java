package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Products;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            Products.deleteProductById(productId);
            response.sendRedirect("View/Admin/AdminHome.jsp");
        } catch (SQLException | ClassNotFoundException e) {
        	request.setAttribute("errorMessage", "Something went wrong...");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            return;
       }
            
      
    }
}

