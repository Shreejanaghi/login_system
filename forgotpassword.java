package login;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class forgotpassword extends view_page {
	private static Scanner input;

	public static void forgot_password() {
		input = new Scanner(System.in);
		System.out.println("Enter your registered username: ");
		String user = input.next();
		String dbuser = "";
		try {
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("select username from security");
		boolean not_found = false;
		while (rs.next()) {
			dbuser = rs.getString(1);
			if (dbuser.equals(user))
				not_found = true;
			else
				not_found =false ;
		}
		if(not_found == false)
			System.out.println("Username not found.");
		else {
			// Generate a random verification code
			int code = (int) (Math.random() * 10000);
			System.out.println("A verification code has been sent to your registeredemail/SMS.");
			System.out.println(code);
			// Ask the user to enter the verification code
			System.out.println("Please enter the verification code:");
			int enteredCode = input.nextInt();
			
			// Compare the entered verification code with the generated one
			if (enteredCode == code) {
				System.out.println("Verification code is correct.\n Please enter a new password:");
				String newPwd = input.next();
				check checkobj = new check();
				if (checkobj.checking(user, newPwd)) {
					String newPwdHash = getMd5(newPwd);
					PreparedStatement pstmt = con.prepareStatement("update security set password = ? where username = ?");
					pstmt.setString(1, newPwdHash);
					pstmt.setString(2, user);
					pstmt.executeUpdate();
					System.out.println("Password changed successfully!");
				}
				else
					System.out.println("New password is not valid.");
				
			}
			else
				System.out.println("Verification code is incorrect.");
			
		}
	}
		 catch (SQLException e) {
			System.out.println(e);
			}

	}
}
