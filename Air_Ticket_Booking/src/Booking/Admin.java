package Booking;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class Admin {
		public void add() throws SQLException, ParseException {
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER FLIGHT DETAILS FOR ADDING !");
			System.out.println("ENTER FLIGHT NAME (A-Z) :");
			String flightName = sc.next();
			while(!checkname(flightName)) {
				System.out.println("FlightName Already Exists Try Another name !");
				flightName = sc.next();
			}
			System.out.println("ENTER FLIGHT ID (ID>200)");
			int flightId = sc.nextInt();
			while(checkid(flightId)) {
				System.out.println("FlightName Already Exists Try Another name !");
				flightId = sc.nextInt();
			}
			System.out.println("ENTER FROM CITY :");
			String fromcity = sc.next();
			System.out.println("ENTER TO CITY :");
			String tocity = sc.next();
			System.out.println("ENTER TRAVEL DATE(YYYY-MM-DDDD) :");
			String traveldate  = sc.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date utilDate = sdf.parse(traveldate);
	        java.sql.Date sqlDate = (java.sql.Date) utilDate;
			System.out.println("ENTER TRAVEL COST :");
			int travelcost = sc.nextInt();
			System.out.println("ENTER CAPACITY (n>50):");
			int capacity = sc.nextInt();
			Connection con = JDBC_connection.getConnection();
            String query = "INSERT INTO flight_Details VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps= con.prepareStatement(query);
            ps.setString(1, flightName);
            ps.setInt(2, flightId);
            ps.setString(3, fromcity);
            ps.setString(4, tocity);
            ps.setDate(5, sqlDate);
            ps.setInt(6, travelcost);
            ps.setInt(7, capacity);
            ps.executeUpdate();
            ps.close();
            con.close();
			
		}
		public void display() throws SQLException {
				Scanner sc = new Scanner(System.in);
			 	System.out.println("ENTER CRITERIAS FOR FILTERING !");
	            System.out.println("ENTER 1 TO VIEW ALL BOOKINGS");
	            System.out.println("ENTER 2 TO FILTER BASED ON FLIGHT_ID");
	            System.out.println("ENTER 3 TO FILTER BASED ON FLIGHT_NAME");
	            System.out.println("ENTER 4 TO FILTER BASED ON TRAVEL_DATE");

	            int adminOption = sc.nextInt();
	            Connection con = JDBC_connection.getConnection();
	            Statement st = con.createStatement();
	            String query = "";

	            if (adminOption == 1) {
	                query = "SELECT * FROM book";
	            } else if (adminOption == 2) {
	                // Filter based on flight_id
	                query = "SELECT * FROM book WHERE flight_Name = 'A' AND travel_Date = '2024-01-10'";
	            } else if (adminOption == 3) {
	                // Filter based on flight_name
	                query = "SELECT * FROM book WHERE flight_Name = 'A'";
	            } else if (adminOption == 4) {
	                // Filter based on travel_date
	                query = "SELECT * FROM book WHERE travel_Date = '2024-01-10'";
	            } else {
	                System.out.println("Invalid option entered!");
	                return;
	            }

	            ResultSet rs = st.executeQuery(query);

	            // Get ResultSet metadata to retrieve column names
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();

	            // Print column headers
	            for (int i = 1; i <= columnsNumber; i++) {
	                System.out.print(rsmd.getColumnName(i) + " | ");
	            }
	            System.out.println("\n-------------------------------------------");

	            // Print data rows
	            while (rs.next()) {
	                for (int i = 1; i <= columnsNumber; i++) {
	                    System.out.print(rs.getString(i) + " | ");
	                }
	                System.out.println();
	            }

		}
		public static boolean checkname(String flightName) throws SQLException {
			Connection con = JDBC_connection.getConnection();
			Statement s = con.createStatement();
			String query = "select from flight_Details where flight_name = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,flightName);
			ResultSet rs  = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)<1;
			}
			return false;
		}
		public static boolean checkid(int id) throws SQLException {
			Connection con = JDBC_connection.getConnection();
			Statement s = con.createStatement();
			String query = "SELECT COUNT(*) FROM flight_Details WHERE flight_Id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs  = ps.executeQuery();
			if(rs.next())
			return rs.getInt(1)<0;
			return false;
		}
}
