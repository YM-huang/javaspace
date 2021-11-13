package third_experience;

import java.util.Scanner;

public class findwinner {
	public static void main(String[] args)
	{
		int a [];
		boolean ak = false;
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		a = new int[n];
		
		for(int i=0 ; i<n ; i++)
		{
			a[i] = cin.nextInt();
		}
		
		int m = cin.nextInt();
		
		for(int j=0 ; j<m ; j++)
		{
			ak = false;
			int t = cin.nextInt();
			for(int k = 0 ; k<n ; k++)
			{
				if(t == a[k])
				{
					ak=true;
				}
			}
			System.out.println(ak);
		}
		
		cin.close();
	}
	
}
