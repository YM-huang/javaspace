package second_experience;

import java.util.Scanner;

public class decode_III {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			long t = cin.nextLong();
			long n=0;
			long m=0;
			for(int i=0;i<t;i++) {
				m=0;
				for(int j=0;j<5;j++) {
					n=cin.nextLong();
					m=(m+n%26)%26;
				}
				m=m%26;
				char A = (char)(m+97);
				System.out.println(A);
			}
			
		}
		cin.close();
	}
}
