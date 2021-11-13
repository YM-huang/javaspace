package second_experience;

import java.util.Scanner;

public class ConvertCelsiustoFahrenheit {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			int a = cin.nextInt();
			double F = ((9.0/5)*a)+32;
			System.out.println(String.format("%.2f", F));
	}
		cin.close();
	}
}
