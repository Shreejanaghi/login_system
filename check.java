package login;

import java.util.regex.Pattern;

public class check extends registration{
	public boolean checking(String name,String pwd) {
		 String pattern1 = "[a-z0-9A-Z_]{3,20}";
		 //String pattern2 = "(?=.[a-z])(?=.[A-Z])(?=.[0-9])(?=.[_@!#$%]).{5,20}";
		 String pattern2 = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[_@!#$%])(?=\\S+$).{8,20}";
		 boolean result = false;
		 if(Pattern.matches(pattern1,name) && Pattern.matches(pattern2,pwd)) {
			//System.out.println("Valid !");
			 result = true;
		 }
		 else {
			 System.out.println("invalid");
		 	result = false;
		 }
		return result;
	}
}
