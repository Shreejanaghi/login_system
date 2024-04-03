package login;

import java.sql.Statement;

public class login_page extends view_page {
	public static void log_in(){
		System.out.println("Enter your registered username: ");
		String user = input.next();
		System.out.println("Enter your password: ");
		String pwd = input.next();
		String pwd2 = getMd5(pwd);
		boolean valid = false;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select username,password from security");
			while(rs.next())
			{
				if(user.equals(rs.getString(1))&& pwd2.equals( rs.getString(2)))
					valid = true;
			}
			if(valid == true)
				System.out.println("You are successfully logged in...");
			else
				System.out.println("Invalid username or password...");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	
}
