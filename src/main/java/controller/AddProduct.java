package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import other.SqlConnector;

@WebServlet("/AddProduct")
@MultipartConfig
public class AddProduct extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("price"));
        // Get form data
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        double priceStr = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String brand = request.getParameter("brand");
        Part image = request.getPart("image");
        String imageP = "products/" + productName + "-" + brand + ".png";
        String imagePath = getServletContext().getRealPath("/Images/") + imageP;
        image.write(imagePath);

       
        try {
            System.out.println(productName+category+priceStr+quantity+brand);
            // Get database connection
            Connection con = SqlConnector.getCon();
            
            // Insert new product into the database
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (name, category, price, quantity, brand, image_url	) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, productName);
            ps.setString(2, category);
            ps.setDouble(3, priceStr);
            ps.setInt(4, quantity);
            ps.setString(5, brand);
            ps.setString(6, imageP);
            int i = ps.executeUpdate();
            System.out.println(i);
            if (i > 0) {
                request.setAttribute("successMsg", "Product added successfully!");
                request.getRequestDispatcher("View/Admin/AdminHome.jsp").forward(request, response);
            }
        } catch (SQLException  e) {
        	request.setAttribute("errorMessage", "Cann't add product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
        } catch (ClassNotFoundException e) {
        	request.setAttribute("errorMessage", "Cann't add product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
		}
    }
}
