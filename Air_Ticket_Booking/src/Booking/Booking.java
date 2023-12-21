package Booking;
import java.sql.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Booking {
	

	public boolean booking(String userName,String userEmail,String userPassword,String userLocation) throws SQLException, ParseException {
		Connection con = JDBC_connection.getConnection();
        	Statement st = con.createStatement();
		String query1 = "SELECT * FROM flight_Details";
		ResultSet rs = st.executeQuery(query1);
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
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("ENTER FLIGHT NAME :(A-Z)");
		String book_flight = sc.next();
		
		System.out.println("ENTER DEPARTURE CITY :");
		String book_arrival = sc.next();
		
		System.out.println("ENTER DESTINATION CITY :");
		String book_destination = sc.next();
		
		System.out.println("ENTER DATE OF TRAVEL (YYYY-MM-DD):");
		String book_date = sc.next();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse(book_date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		String query = "select count(*) from book where flight_Name = ? and travel_Date = ?";
		PreparedStatement ps1 = con.prepareStatement(query);
		ps1.setString(1,book_flight);
		ps1.setDate(2, sqlDate);
		ResultSet rs1 =  ps1.executeQuery();
		int bookingcount =0;
		if(rs1.next()) {
		 bookingcount = rs1.getInt(1);
		}
		query = "select flight_Capacity from flight_Details where flight_Name = ?";
		PreparedStatement ps2 = con.prepareStatement(query);
		ps2.setString(1,book_flight);
		ResultSet rs2 = ps2.executeQuery();
		int capacity1 =0;
		if(rs2.next()) {
			capacity1 = rs2.getInt(1);
		}
		if(bookingcount<capacity1) {
			System.out.println("ENTER TOTAL NO OF PERSONS :");
			int totalpersons = sc.nextInt();
		query = "insert into booking values(?,?,?,?,?)";
		
		for( int i=0;i<totalpersons;i++) {
			System.out.println("ENTER PERSON "+(i+1)+" NAME");
			String username = sc.next();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2,book_flight);
			ps.setString(3,book_arrival);
			ps.setString(4,book_destination);
			ps.setDate(5,sqlDate);
			ps.execute();
		}
		return true;
		}else {
			return false;
		}
		}
	}
}
