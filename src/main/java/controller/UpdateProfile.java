package controller;

import model.User;
import model.UserDao;
import other.SqlConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateProfile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("Uid") == null) {
            response.sendRedirect("View/Auth/Login.jsp");
            return;
        }
        Object userObj = session.getAttribute("Uid");
        int userId = Integer.parseInt(userObj.toString());

        try {
        	Connection connection = SqlConnector.getCon();
        	UserDao userDao = new UserDao(connection);
        	User user = userDao.getCurrentUser(userId);


            request.setAttribute("user", user);
            request.getRequestDispatcher("/View/User/Profile.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            // Handle invalid number format
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException e) {
            // Handle database errors
            request.setAttribute("errorMessage", "Error retrieving user");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
        	request.setAttribute("errorMessage", "Error retrieving user");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
