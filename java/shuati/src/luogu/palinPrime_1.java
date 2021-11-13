package luogu;

import java.util.*;

public class palinPrime_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		int a = cin.nextInt();
		int b = cin.nextInt();
		for (int i=a; i<=b; i++) {
			//如果可以被2、3、5整除，必然不满足条件
			if(i!= 2 && i!=3 && i!=5 && (i%2==0 || i%3==0 || i%5==0)) {
				continue;
			}
			if(isPlalindrome(i)) {
				if(prime(i)) {
					System.out.println(i);
				}
			}
		}
	}
//	
//	static boolean isprime(int x)//判断素数函数；
//	{
//	    for(int m=2;m<=Math.pow(x, 1.0/2);m++)
//	    {
//	        if(x%m==0)
//	            return false;
//	    }
//	    return true;
//	}
//	
//	static boolean ispalin(int x)
//	{
////		String str = Integer.toString(x);
////		String reverse = new StringBuffer(str).reverse().toString();
//		if(x == rev(x))
//		{
//			return true;
//		}
//		else
//			return false;
//	}
//	
//	static int rev(int x)//反转这个数，如果与原数相等则是回文数；
//	{
//	    int new_x=0;
//	    while(x!=0)
//	    {
//	        new_x = x%10+new_x*10;
//	        x=x/10;
//	    }
//	    return new_x;
//	}
	public static boolean prime(int num){
        if (num % 2 == 0) {
            return false;
        }
        for(int i=3;i<=Math.sqrt(num);i+=2){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
	
    public static boolean isPlalindrome(int num){
        int one = num,y=0;
        do{
            y=y*10+one%10;
        }while((one/=10)!=0);
        if(y==num){
            return true;
        }else{
            return false;
        }
    }
    
//    public static void main(String args[]) {
//        Scanner scanner = new Scanner(System.in);
//        int min = scanner.nextInt();
//        int max = scanner.nextInt();
//        if(max>10000000){
//            for(int i=min;i<=10000000;i++){
//                if(prime(i)&&isPlalindrome(i)){
//                    System.out.println(i);
//                }
//            }
//        }else{
//            for(int i=min;i<=max;i++){
//                if(prime(i)&&isPlalindrome(i)){
//                    System.out.println(i);
//                }
//            }
//        }
//        return;
//    }

}
