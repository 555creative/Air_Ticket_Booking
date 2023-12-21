package Booking;

import java.sql.*;

public class JDBC_connection {
	private static String url = "jdbc:mysql://127.0.0.1:3306/ak";
    private static String username = "root";
    private static  String password = "Arun2003@";
    public static  Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }
}
