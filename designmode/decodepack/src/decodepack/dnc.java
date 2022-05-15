package decodepack;

import java.util.Scanner;

public class dnc {
	public static void main(String[] args) throws Exception {
		System.out.println("请选择要进行的操作：");
		System.out.println("1：加密");
		System.out.println("2：解密");
		Scanner cin = new Scanner(System.in);
		int t1 = cin.nextInt();
		if( t1 == 1 ){
			System.out.println("请选择你要加密的方式：");
			System.out.println("1：异或");
			System.out.println("2：密钥");	
			int t2 = cin.nextInt();
			if( t2 == 1 ){
				System.out.println("请输入要加密的文件地址（加密完成后文件会删除）：");
				String s1 = cin.next();
				System.out.println("请输入加密文件输出地址：");
				String s2 = cin.next();
				encodeanddecodemain mode1 = new encodeanddecode1();		
				mode1.performDecode(s1, s2);
			}
			else if(t2 == 2) {
				System.out.println("请输入密钥：");
				String s = cin.next();
				encodeanddecodemain mode2 = new encodeanddecode2(s);
				System.out.println("请输入要加密的文件地址（加密完成后文件会删除）：");
				String s1 = cin.next();
				System.out.println("请输入加密文件输出地址：");
				String s2 = cin.next();
				mode2.performEncode(s1, s2);				
			}
			else {
				System.out.println("请输入有效的字符！");
			}
		}
		else if( t1 == 2 ){
			System.out.println("请选择你要解密的方式：");
			System.out.println("1：异或");
			System.out.println("2：密钥");	
			int t2 = cin.nextInt();
			if( t2 == 1 ){
				System.out.println("请输入要加密的文件地址（加密完成后文件会删除）：");
				String s1 = cin.next();
				System.out.println("请输入加密文件输出地址：");
				String s2 = cin.next();
				encodeanddecodemain mode1 = new encodeanddecode1();	
				mode1.performDecode(s1, s2);
			}
			else if(t2 == 2) {
				System.out.println("请输入密钥：");
				String s = cin.next();
				encodeanddecodemain mode2 = new encodeanddecode2(s);
				System.out.println("请输入要加密的文件地址（加密完成后文件会删除）：");
				String s1 = cin.next();
				System.out.println("请输入加密文件输出地址：");
				String s2 = cin.next();
				mode2.performDecode(s1, s2);
				
			}
			else {
				System.out.println("请输入有效的字符！");
			}
		}
		else {
			System.out.println("请输入有效的字符！");
		}

		cin.close();
	}


}
