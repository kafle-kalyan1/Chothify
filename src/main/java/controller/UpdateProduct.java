package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/UpdateProduct")
@MultipartConfig
public class UpdateProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // format with hours and minutes only
        String formattedTime = currentTime.format(formatter).replace(":", "");

        String productId = request.getParameter("id");
        String productName = request.getParameter("name");
        String productCategory = request.getParameter("category");
        String productPrice = request.getParameter("price");
        String productQuantity = request.getParameter("quantity");
        String productBrand = request.getParameter("brand");
        Part image = request.getPart("image");
        String imageP = "products/" + productName + "-" + formattedTime + ".png";
        String imagePath = getServletContext().getRealPath("/Images/") + "/" + imageP;

        try {
            Connection con = SqlConnector.getCon();
            PreparedStatement ps = con.prepareStatement("UPDATE products SET name=?, category=?, price=?, quantity=?, brand=?, image_url=? WHERE id=?");
            ps.setString(1, productName);
            ps.setString(2, productCategory);
            ps.setString(3, productPrice);
            ps.setString(4, productQuantity);
            ps.setString(5, productBrand);
            if (image.getSize() > 0) {
                InputStream is = image.getInputStream();
                Files.copy(is, Paths.get(imagePath));
                ps.setString(6, imageP);
            } else {
                ps.setString(6, null);
            }
            ps.setString(7, productId);
            ps.executeUpdate();
            response.sendRedirect(request.getContextPath() + "/View/Admin/AdminHome.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Can't update product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            return;
        }
    }
}
