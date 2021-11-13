import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			int n = cin.nextInt();
			int t = 0;
			for(int i=1;i<=n;i++)
				t=t+i;
			System.out.println(t);
				
		}
		cin.close();

	}

}
