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

import model.ShowOrders;
import other.SqlConnector;

@WebServlet("/orderList")
public class OrderList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderList() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all orders from the database
        List<ShowOrders> orderList = null;
        try {
            Connection conn = SqlConnector.getCon();
            ShowOrders orders = new ShowOrders(0, 0, 0, 0, null, null, null);
            orderList = orders.showAllOrders(conn);
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
        	request.setAttribute("errorMessage", "Error retrieving user"+e);
            request.getRequestDispatcher("View/error.jsp").forward(request, response);
           
        }
        
        // Set the order list as an attribute of the request object
        request.setAttribute("orderList", orderList);
        System.out.println(orderList);
        
        // Forward to the JSP page to display the orders
        request.getRequestDispatcher("View/Admin/Orders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
