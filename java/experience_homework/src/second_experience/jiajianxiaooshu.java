package second_experience;

import java.util.Scanner;

public class jiajianxiaooshu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {

			float a1 = cin.nextFloat();
			float a2 = cin.nextFloat();
			int t = cin.nextInt();
			float n = 0;

			for(int i=0;i<t;i++)
			{
				float m =cin.nextFloat();
				n = n + m;
			}
			

			if((n+a1) == a2) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
		}
		cin.close();
	}

}

