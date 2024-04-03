package login;

import static login.registration.register;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class view_page {
	static Scanner input = new Scanner(System.in);
	static Connection con = null ;
	static Statement st; 
	static ResultSet rs ;
	public static void main(String[] args)
	{
		try {
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/au","root","mysql30#");
			st =con.createStatement();
			while (true) {
				System.out.println("Select an option: ");
				System.out.println("1. Registration");
				System.out.println("2. Login");
				System.out.println("3. Forgot Password");
				System.out.println("4.Exit");
				int option = input.nextInt();
				switch (option) {
				case 1:
					registration regobj = new registration();
					register();
					break;
				case 2:
					login_page loginobj = new login_page();
					login_page.log_in();
					break;
				case 3:
					forgotpassword forgotPwdObj = new forgotpassword();
					forgotpassword.forgot_password();
					break;
				case 4:
					System.out.println("Exit");
					return;
				default:
				System.out.println("Invalid option selected. Try again.");
				}
				}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//switch()
	}
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("Md5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1,messageDigest);
			String hashtext = no.toString(16);
			while(hashtext.length()<32)
				hashtext = "0" + hashtext;
			return hashtext;
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException (e);
		}
	}
}
