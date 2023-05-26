package other;
import java.sql.*;
public class SqlConnector {

		public static Connection getCon() throws ClassNotFoundException, SQLException
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/clothify";
				String username = "root";
				String password = "";
				
				Connection con = DriverManager.getConnection(url,username,password);
//				public Connection getConnection() throws ClassNotFoundException, SQLException {
					
					
				return con;
				}
				
				
			
			catch(Exception e) {
				System.out.println("Error"+e);
				return (null);
				
			}
		}


	}


