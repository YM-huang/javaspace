package second_experience;

import java.util.Scanner;

public class decode_I {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			int t = cin.nextInt();
			int n=0;
			int m=0;
			for(int i=0;i<t;i++) {
				m=0;
				for(int j=0;j<5;j++) {
					n=cin.nextInt();
					m=m+n;
				}
				m=m%26;
				char A = (char)(m+97);
				System.out.println(A);
			}
			
		}
		cin.close();
	}
	
}
