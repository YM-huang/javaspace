package second_experience;

import java.util.Scanner;

public class decode_II {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			double t = cin.nextDouble();
			double n=0;
			double m=0;
			for(int i=0;i<t;i++) {
				m=0;
				for(int j=0;j<5;j++) {
					n=cin.nextDouble();
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
