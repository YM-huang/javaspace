package third_experience;

import java.util.Scanner;

public class substring_URL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		String web;
		int n,m;
		
		while(cin.hasNext())
		{	
			web = cin.next();
			n = web.indexOf("/");
			m = web.indexOf("/",n+2);
			System.out.println(web.substring(n+2,m));
		}
		
		
		
		
		cin.close();
	}

}
