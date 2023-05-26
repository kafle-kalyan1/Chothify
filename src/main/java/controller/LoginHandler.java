package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import other.SqlConnector;

@WebServlet("/login")
public class LoginHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = SqlConnector.getCon();
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (BCrypt.checkpw(password, storedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("Uid", rs.getInt("id"));

                    session.setAttribute("email", email);
                    if (rs.getBoolean("isAdmin")) {
                        session.setAttribute("isAdmin", true);
                        response.sendRedirect("../Admin/AdminHome.jsp");
                    } else {
                        response.sendRedirect("../User/Home.jsp");
                        session.setAttribute("isAdmin", false);
                    }
                } else {
                	request.setAttribute("errorMessage", "Password Error please try again");
                    request.getRequestDispatcher("../error.jsp").forward(request, response);

                    return;
                }
            } else {
            	request.setAttribute("errorMessage", "Email Never registered try again or register.");
                request.getRequestDispatcher("../error.jsp").forward(request, response);

                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("errorMessage", "Can't Login right now");
            request.getRequestDispatcher("../error.jsp").forward(request, response);

            return;
        }
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
