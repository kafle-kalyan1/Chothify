package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDao;
import other.SqlConnector;
	

@WebServlet("/listusers")
public class ListUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		 Connection con = SqlConnector.getCon();
    	        UserDao userDao = new UserDao(con);
    	        List<User> users = userDao.getAllUsers();
    	        request.setAttribute("users", users);
    	        request.getRequestDispatcher("View/Admin/ShowUser.jsp").forward(request, response);
    	} catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Cann't add product right now");
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
            
            return;
    	}
    	
    }
    
    
}
