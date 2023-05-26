package controller;

import model.Products;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProduct extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public EditProduct() {
        super();
        System.out.println("EditProduct servlet called");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("EditProduct servlet called");

        String id = request.getParameter("id");
        String productId = request.getParameter("id");
        System.out.println("Product ID: " + productId);

        try {
            Products product = Products.getProductById(Integer.parseInt(id));
            request.setAttribute("product", product);
            request.getRequestDispatcher("/View/Admin/EditProduct.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        	request.setAttribute("errorMessage", "Cann't add product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
        } catch (ClassNotFoundException | SQLException e) {
            // Handle database errors
        	request.setAttribute("errorMessage", "Cann't add product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
       }
        }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("EditProduct servlet called");

        doGet(request, response);
    }
}
