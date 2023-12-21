package Booking;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OTP {
	
	private static final String DIGITS = "0123456789";
    private static final int OTP_LENGTH = 6;
    
    public String generateOTP() {
       SecureRandom random = new SecureRandom();
       StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(DIGITS.length());
            otp.append(DIGITS.charAt(randomIndex));
        }
        return otp.toString();
    }
    
	
	@SuppressWarnings("unused")
	public boolean sendotp(String userEmail,String subject,String content) {
		Scanner sc = new Scanner(System.in);
        try {
			if(true) {
			
			Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        properties.put("mail.smtp.host", "smtp.gmail.com"); 
	        properties.put("mail.smtp.port", "587");
	        String htmlContent="<!DOCTYPE html>\r\n"

        		+ "<html lang=\"en\">\r\n"

        		+ "<head>\r\n"

        		+ "    <meta charset=\"UTF-8\">\r\n"

        		+ "    <title>OTP Email</title>\r\n"

        		+ "    <style>\r\n"

        		+ "        body {\r\n"

        		+ "            font-family: Arial, sans-serif;\r\n"

        		+ "            margin: 0;\r\n"

        		+ "            padding: 0;\r\n"

        		+ "            background-color: #f4f4f4;\r\n"

        		+ "        }\r\n"

        		+ "\r\n"

        		+ "        .container {\r\n"

        		+ "            width: 80%;\r\n"

        		+ "            margin: auto;\r\n"

        		+ "            overflow: hidden;\r\n"

        		+ "            padding: 20px;\r\n"

        		+ "            background: #fff;\r\n"

        		+ "            border-radius: 8px;\r\n"

        		+ "            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.1);\r\n"

        		+ "        }\r\n"

        		+ "\r\n"

        		+ "        h1 {\r\n"

        		+ "            color: #333;\r\n"

        		+ "            margin-bottom: 20px;\r\n"

        		+ "        }\r\n"

        		+ "\r\n"

        		+ "        p {\r\n"

        		+ "            color: #555;\r\n"

        		+ "            font-size: 16px;\r\n"

        		+ "            line-height: 1.6;\r\n"

        		+ "        }\r\n"

        		+ "\r\n"

        		+ "        .otp {\r\n"

        		+ "            font-size: 36px;\r\n"

        		+ "            font-weight: bold;\r\n"

        		+ "            padding: 10px;\r\n"

        		+ "            background-color: #f9f9f9;\r\n"

        		+ "            border: 1px solid #ddd;\r\n"

        		+ "            border-radius: 5px;\r\n"

        		+ "            display: inline-block;\r\n"

        		+ "        }\r\n"

        		+ "\r\n"

        		+ "        .footer {\r\n"

        		+ "            margin-top: 20px;\r\n"

        		+ "            border-top: 1px solid #ddd;\r\n"

        		+ "            padding-top: 10px;\r\n"

        		+ "            color: #777;\r\n"

        		+ "            font-size: 14px;\r\n"

        		+ "        }\r\n"

        		+ "    </style>\r\n"

        		+ "</head>\r\n"

        		+ "<body>\r\n"

        		+ "    <div class=\"container\">\r\n"

        		+ "        <h1>Your One-Time Password (OTP)</h1>\r\n"

        		+ "        <p>Please use the following OTP to proceed:</p>\r\n"

        		+ "        <div class=\"otp\">"+subject+"</div> <!-- Replace with your generated OTP -->\r\n"

        		+ "        <div class=\"footer\">\r\n"

        		+ "            <p>If you didn't request this OTP or need assistance, please contact support.</p>\r\n"

        		+ "        </div>\r\n"

        		+ "    </div>\r\n"

        		+ "</body>\r\n"

        		+ "</html>\r\n"

        		+ "";
			Session session = Session.getInstance(properties, new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication("555creativeak2003@gmail.com", "lqlt mpun xrea ujdw");
			    }
			});
				
			    Message message = new MimeMessage(session);
			    message.setFrom(new InternetAddress("555creativeak2003@gmail.com"));
			    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			    message.setSubject(subject);
			    message.setContent(htmlContent, "text/html");
			    Transport.send(message);
			    System.out.println(content);
			    
			}else{
				System.out.println("OTP SENDING FAILED...!");
			}
		} catch (MessagingException e) {
			System.out.println(e);
		}
        return false;
	}
}
