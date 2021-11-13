package third_experience;

import java.util.Scanner;

public class dianming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		int m , n , t;
		int a[],b[];
		while(cin.hasNext())
		{
			m = cin.nextInt();
			n = cin.nextInt();
			a = new int[m];
			b = new int[n];
			for(int i=0;i<m;i++)
			{
				a[i] = cin.nextInt();
			}
			for(int j=0;j<n;j++)
			{
				b[j] = cin.nextInt();
			}
			t = cin.nextInt();
			if(t > b[n-1])
			{
				System.out.println("YES");
			}
			else
			{
				System.out.println("NO");
			}
		}
		
		
		cin.close();
	}

}
