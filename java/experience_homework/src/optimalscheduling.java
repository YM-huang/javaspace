import java.util.Scanner;

public class optimalscheduling {

	static int n;
	static int k;
	static int [] x = new int [100];//»úÆ÷
	static int [] x1 = new int [100];//×÷Òµ
	static int maxnum = Integer.MAX_VALUE;
	static void task(int level)
	{
		if(level>n){
			int temp=0;
			for(int i=1;i<=k;i++){
				if(x[i]>temp){
					temp=x[i];
				}
			}
			if(temp<maxnum){
				maxnum=temp;
			}
		}
		else{
			for(int i=1;i<=k;i++){
				x[i]=x[i]+x1[level];
				task(level+1);
				x[i]=x[i]-x1[level];
			}
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		for (int i = 1; i < n+1; i++) {
			x1[i]=sc.nextInt();
		}
		task(1);
		System.out.println(maxnum);
	}
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
//		int k = sc.nextInt();
//		int [] a = new int [k+1];
//		int [] num = new int [n+1];
//		int [] num1 = new int [n+1];
//		for (int i = 1; i < num.length; i++) {
//			num[i]=sc.nextInt();
//		}
//		Arrays.sort(num);
//		for (int i = 1; i < num1.length; i++) {
//			num1[i]=num[num.length-i];
//		}
//		int b = 0;
//		int temp = 1;
//		for (int i = 0; ; i++) {
//			for (int j = 1; j <=k; j++) {
//				if(a[j]==0 && temp !=n+1){
//					a[j]=num1[temp];
//					temp++;
//				}
//				if(a[j]!=0){
//					b=-1;
//					a[j]-=1;
//				}
//			}
//			if(b==0){
//				System.out.println(i);
//				break;
//			}
//			b=0;
//		}
//	}
}
