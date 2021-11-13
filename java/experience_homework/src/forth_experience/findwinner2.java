package forth_experience;

import java.util.Scanner;

import java.util.*;

public class findwinner2 {

	public static void main(String[] args) {
//		long a [];
		boolean ak = false;
		Map map = new HashMap();
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
//		a = new long[n];
		for(int i=0 ; i<n ; i++)
		{
//			/* a[i] = cin.nextLong(); */
			map.put(cin.nextInt(), i);
		}
		
		int m = cin.nextInt();
		
		for(int j=0 ; j<m ; j++)
		{
			ak = false;
			int t = cin.nextInt();
//			for(int k = 0 ; k<n ; k++)
//			{
//				if(t == a[k])
//				{
//					ak=true;
//				}
//			}
			ak = map.containsKey(t);
			System.out.println(ak);
		}
		
		cin.close();

	}

}
