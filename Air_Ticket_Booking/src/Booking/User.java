package Booking;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
    
	public boolean login(String userName,String userEmail,String userPassword,String userLocation) throws SQLException{
		
		Scanner sc = new Scanner(System.in);
		Connection con= JDBC_connection.getConnection();
		Statement st1= con.createStatement();
		String query = "select user_Password from sign where user_Email = ?";
		PreparedStatement st= con.prepareStatement(query);
		st.setString(1, userEmail);
		ResultSet rs = st.executeQuery();
		if (rs.next()){
            String storedPassword = rs.getString("user_Password");
            if (userPassword.equals(storedPassword)) {
                	return true;
            } else {
                System.out.println("Invalid password! Login failed.");
                System.out.println("DO YOU WANT TO CHANGE YOUR PASSWORD ? (yes/no)");
                if(sc.next().equals("yes")) {
                	OTP otp = new OTP();
                	while(true) {
                	String generatedOTP = otp.generateOTP();
                	boolean verifyotp = otp.sendotp(userEmail,generatedOTP,"ENTER OTP FROM YOUR MAIL");
                	if(verifyotp){
                		if(sc.nextInt() == Integer.parseInt(generatedOTP)) {
                		System.out.println("Enter NEW PASSWORD");
                		String newpassword = sc.next();
                		query = "UPDATE sign set user_Password = ? where user_Email = userEmail;";
                		PreparedStatement st2= con.prepareStatement(query);
                		st2.setString(1, newpassword);
                		ResultSet rs1 = st.executeQuery();
                		verifyotp = otp.sendotp(userEmail,"YOUR PASSWORD UPDATED","PASSWORD UPDATED "+userName);
                		return true;
                		}else {
                			System.out.println("INCORRECT OTP !");
                			System.out.println("DO YOU WANT TO RESEND OTP ?(yes/no)");
                			if(sc.next().equals("no")) return false;
                		}
                	}else {
                		System.out.println("OTP FAILED !");
                		System.out.println("DO YOU WANT TO RESEND OTP ? (yes/no)");
                		if(sc.next().equals("no")) break;
                	}
                	}
                }
                }
            }
         else {
            System.out.println("User not found! Login failed.");
        }
		return false;
	}
	
	public void signup(String userName,String userEmail,String userPassword, String userLocation) throws SQLException {
		
		// storing all signuped user details in HashMap for verification 
		Connection con= JDBC_connection.getConnection();
		Statement st1= con.createStatement();
		Scanner sc = new Scanner(System.in);
		// Validating userName

		while(!uservalid(userName)){			            
            System.out.println("The username is already present.Plz... Enter the valid username.");
            userName = sc.next();
        }
		
		//  Validating userEmail
		
		while(!mailverify(userEmail)){							        
            System.out.println("Plz... enter the vaild email id for login");
            userEmail=sc.next();
        }
		
		//  Validating userPassword
		
		while(!validation(userPassword)){						
            System.out.println("Plz.. enter the valid password with the one special char, one number ,one captial letter, one small charater without spaces");
            userPassword=sc.next();
        }
		
		
        
        //inserting new user datas into database
        PreparedStatement st= con.prepareStatement("Insert into sign values(?,?,?,?)");
        st.setString(1,userName);
        st.setString(2,userEmail);
        st.setString(3,userPassword);
        st.setString(4,userLocation);
        st.execute();
        System.out.println("YOU HAVE SUCCESSFULLY SIGNED UP!");
		
	}
	
	static boolean validation(String str){	
		
		// password validation function
		String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    static boolean uservalid(String str){
    	
        return str.length()>5;  
    }
    static boolean mailverify(String mail){	
    	
    	// user mailid validation function
    	
    	String emailPattern = "^[a-zA-Z0-9_.+-]+@gmail\\.com$"; 
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
	
}
