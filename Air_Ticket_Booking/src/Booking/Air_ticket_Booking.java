package Booking;
import java.util.*;
import java.sql.*;
import java.text.ParseException;

public class Air_ticket_Booking {
	

	public static void main(String[] args) throws SQLException, ParseException{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO TICKET BOOKING ....!");
		System.out.println("Enter 1 for LOGIN");
		System.out.println("Enter 2 for SIGNUP");
		System.out.println("Enter 3 for ADMINLOGIN");
		int userOption = sc.nextInt();
		System.out.println("ENTER YOUR NAME");
		String userName = sc.next();
		System.out.println("ENTER YOUR EMAIL");
		String userEmail = sc.next();
		System.out.println("ENTER YOUR PASSWORD");
		String userPassword = sc.next();
		System.out.println("ENTER YOUR LOCATION");
		String userLocation = sc.next();
		User u = new User();
		if(userOption == 1){
			boolean verify = u.login(userName,userEmail,userPassword,userLocation);
			if(verify){
				System.out.println("SUCCESSFULLY LOGINED "+userName+" !");
				Booking b = new Booking();
				while(true){
					boolean bookingverify = b.booking(userName,userEmail,userPassword,userLocation);
					if(bookingverify){
						OTP otp = new OTP();
						otp.sendotp(userEmail,"BOOKING CONFIRMED !","BOOKING CONFIRMED "+userName);
						System.out.println("SUCCESFULLY BOOKED "+userName+" !");
						break;
					}else{
						System.out.println("BOOKING FAILED !");
						System.out.println("TRY ANOTHER BOOKING !");
					}
				}
			}
		}else if(userOption == 2){
			
			u.signup(userName,userEmail,userPassword,userLocation);
			System.out.println("Do You Want to Log in "+userName+" ? (yes/no)");
			if(sc.next().equals("yes")){
				u.login(userName,userEmail,userPassword,userLocation);
				System.out.println("Do You Want to Book tickets "+userName+" ? (yes/no)");
				if(sc.next().equals("yes")){
					Booking b = new Booking();
					while(true){
						boolean bookingverify = b.booking(userName,userEmail,userPassword,userLocation);
						if(bookingverify){
							System.out.println("SUCCESFULLY BOOKED "+userName+" !");
							
							break;
						}else {
							System.out.println("BOOKING FAILED !");
							System.out.println("TRY ANOTHER BOOKING ? (yes/no)");
							if(sc.next().equals("no")) break;
						}
					}
				}
			}
			else System.out.println("THANKYOU "+userName+".....!");
		}else{
			
			boolean verify = u.login(userName,userEmail,userPassword,userLocation);
			if(verify){
				
				Admin a = new Admin();
				System.out.println("WELCOME ADMIN !");
				System.out.println("Enter 4 for ADDING FLIGHTS");
				System.out.println("Enter 5 for BOOKING DETAILS");
				int adminOption = sc.nextInt();
				if(adminOption == 4) {
					a.add();
					System.out.println("SUCCESSFULLY ADDED FLIGHT !");
				}else if (adminOption == 5) {
					a.display();
				}
			}
		}
		sc.close();
	}

}
