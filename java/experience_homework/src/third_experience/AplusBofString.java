package third_experience;

import java.util.Scanner;

public class AplusBofString {

//	public static boolean canParseInt(String str){
//
//		if(str == null){ //��֤�Ƿ�Ϊ��
//
//			return false;
//
//		}
//
//		return str.matches("\\d+"); //ʹ��������ʽ�жϸ��ַ����Ƿ�Ϊ���֣���һ��\��ת�����\d+��ʾƥ��1����  //����������֣�"+"��"*"���ƣ�"*"��ʾ0������
//
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		String A,B;
		int m,n;
		int ifa,ifb;
//		boolean ifnum  = false;
		while(cin.hasNext())
		{
			m=0;
			n=0;
			ifa=1;
			ifb=1;
			A = cin.next();
			for(int i = 0;i<A.length();i++)
			{
//				ifnum = canParseInt(A.charAt(i));
				char a= A.charAt(i);
				if(m==0 && a=='-') 
				{
					  ifa=-1;
				}
				if(a>='0'&&a<='9')
				{
					m=m*10+Integer.valueOf(a)-48;
				}
			}
			B = cin.next();
			for(int j = 0;j<B.length();j++)
			{
//				ifnum = canParseInt(A.substring(j,j));
				char b= B.charAt(j);
				if(n==0 && b=='-') 
				{
					  ifb=-1;
				}
				if(b>='0'&&b<='9')
				{
					n=n*10+Integer.valueOf(b)-48;
				}
			}
			int result=m*ifa+n*ifb;
			System.out.println(result);
		}
		
		
		
		cin.close();
	}

}
//Integer.valueOf("12")