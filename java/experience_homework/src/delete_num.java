import java.util.Scanner;

public class delete_num {
	public static void main(String[] args) {
		String a = null;
		int n = -1;
		Scanner input = new Scanner(System.in);
		 
		 a = input.nextLine();
		 n = input.nextInt();
		 
		 char a1[] = a.toCharArray();
		 
		 if(n == 0) {
		 	System.out.println(a);
		 }
		 
		 if(a1.length <= n) {
		 	System.out.println("�ַ������Ȳ���");
		 }else {
		 	int len = n;
		 	int i = 0;
		 	while(len > 0&&i<a.length()-1) {
		 		//������򣬾�ɾ�������
		 		if(a1[i]>a1[i+1]) {
		 			for(int j = i;j<a1.length-1;j++) {
		 				a1[j] = a1[j+1];	
		 			}
		 			i = -1;
		 			len--;
		 		}
		 		i++;

		 	}
		 	for(int j = 0;j < a1.length-n;j++) {
		 		
		 		System.out.print(a1[j]);
		 		
		 	}
		 }
	}
}
