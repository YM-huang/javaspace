package forth_experience;

import java.util.*;
import java.util.Scanner;

public class reverseorder {

	public static void main(String[] args) {
		List a = new ArrayList();
		Scanner cin = new Scanner(System.in);
		int m = 0;
		String s;
		while(cin.hasNext())
		{
			s = cin.next();
			if(s.contains("."))
			{
				s = s.replace(".","");
				a.add(m,s);
				for(int j = 0; j <a.size();j++) 
				{
					System.out.print(a.get(j)+" ");
				}
				a.clear();
			}
			else
			{
				a.add(m,s);
			}
//			m=m+1;
		}
		cin.close();
	}

}
