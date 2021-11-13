package second_experience;

import java.util.Scanner;

public class sushudui {

	public static boolean ifsushu(int a){
		boolean k=true;
		int t=2;
		if(a==0||a==1) {
			return false;
		}
		int j=(int)Math.sqrt(a);
		while(t<=j)
		{	if(a%t==0)
			k=false;
			t=t+1;
		}
		if(k) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			int a = cin.nextInt();
			for(int i=0;i<a-1;i++) {
				if(ifsushu(i)&&ifsushu(i+2)) {
					System.out.println(i+" "+(i+2));
				}
			}
			System.out.println("Done");
		}
		cin.close();
	}

}
