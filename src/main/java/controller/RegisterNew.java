package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import other.SqlConnector;

@WebServlet("/Register")
@MultipartConfig
public class RegisterNew extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data from request
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");


        // Check if password and confirm password match
        if (!password.equals(confirmPassword)) {
        	request.setAttribute("errorMessage", "Password did't match try again");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
        }


        String hashedPassword = hashPassword(password);


        Part filePart = request.getPart("image");
        String imageP = "profile/"+email+".png";
        String imagePath = getServletContext().getRealPath("/Images/") + imageP;
        filePart.write(imagePath);
        System.out.println();


        try {
            Connection con = SqlConnector.getCon();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users (fullName, email, address, phone, image, password) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, imageP);
            ps.setString(6, hashedPassword);

            ps.executeUpdate();

            response.sendRedirect("View/User/Home.jsp?msg=done"); // Redirect to success page after successful registration

        } catch (SQLException | ClassNotFoundException e) {
        	request.setAttribute("errorMessage", "Cann't Register please try again...");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
           }
        }
    

    
    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
