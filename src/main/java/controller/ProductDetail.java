package controller;

import model.Products;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetail extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public ProductDetail() {
        super();
        System.out.println("EditProduct servlet called");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String productId = request.getParameter("id");
        System.out.println("Product ID: " + productId);

        try {
            Products product = Products.getProductById(Integer.parseInt(id));
            request.setAttribute("product", product);
            request.getRequestDispatcher("/View/User/Product-Detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid number format
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle database errors
            request.setAttribute("errorMessage", "Error retrieving product");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	

        doGet(request, response);
    }
}
