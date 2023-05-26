package controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDao;
import other.SqlConnector;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/SaveProfile")
@MultipartConfig
public class SaveProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveProfile() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("email");
        String email = obj != null ? obj.toString() : null;

        if (email == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }

        String fullName = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String sysPassword = request.getParameter("sysPassword");
        String password = request.getParameter("password");
        
        // Validate inputs
        if (fullName == null || fullName.trim().isEmpty() || address == null || address.trim().isEmpty()
                || phone == null || phone.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            return;
        }

        System.out.println(sysPassword);
        System.out.println(password);

        try {
            Object userObj = session.getAttribute("Uid");
            int userId = Integer.parseInt(userObj.toString());
            Connection connection = SqlConnector.getCon();
            UserDao userDao = new UserDao(connection);
            User user = userDao.getCurrentUser(userId);

            // Compare entered password with hashed password using Bcrypt hashing
            if (BCrypt.checkpw(password, user.getPassword())) {
            	// Check if there is any change in user data
                if (user.getFullName().equals(fullName) && user.getAddress().equals(address)
                        && user.getPhone().equals(phone)) {
                    request.setAttribute("successMessage", "No changes made to profile");
                    request.getRequestDispatcher("View/User/Home.jsp").forward(request, response);
                    return;
                }

                // Update user data
                user.setFullName(fullName);
                user.setAddress(address);
                user.setPhone(phone);
                userDao.updateUser(user);

                request.setAttribute("successMessage", "Profile updated successfully");
                request.getRequestDispatcher("View/User/Home.jsp").forward(request, response);
            }
            else {
            	 request.setAttribute("errorMessage", "Wrong Password please try again");
                 request.getRequestDispatcher("View/error.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", "Error updating profile , "+e);
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
        }
    }
}
