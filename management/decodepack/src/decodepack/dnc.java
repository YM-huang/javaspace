package decodepack;

import java.util.Scanner;

public class dnc {
	public static void main(String[] args) throws Exception {
		System.out.println("��ѡ��Ҫ���еĲ�����");
		System.out.println("1������");
		System.out.println("2������");
		Scanner cin = new Scanner(System.in);
		int t1 = cin.nextInt();
		if( t1 == 1 ){
			System.out.println("��ѡ����Ҫ���ܵķ�ʽ��");
			System.out.println("1�����");
			System.out.println("2����Կ");	
			int t2 = cin.nextInt();
			if( t2 == 1 ){
				System.out.println("������Ҫ���ܵ��ļ���ַ��������ɺ��ļ���ɾ������");
				String s1 = cin.next();
				System.out.println("����������ļ������ַ��");
				String s2 = cin.next();
				encodeanddecodemain mode1 = new encodeanddecode1();		
				mode1.performDecode(s1, s2);
			}
			else if(t2 == 2) {
				System.out.println("��������Կ��");
				String s = cin.next();
				encodeanddecodemain mode2 = new encodeanddecode2(s);
				System.out.println("������Ҫ���ܵ��ļ���ַ��������ɺ��ļ���ɾ������");
				String s1 = cin.next();
				System.out.println("����������ļ������ַ��");
				String s2 = cin.next();
				mode2.performEncode(s1, s2);				
			}
			else {
				System.out.println("��������Ч���ַ���");
			}
		}
		else if( t1 == 2 ){
			System.out.println("��ѡ����Ҫ���ܵķ�ʽ��");
			System.out.println("1�����");
			System.out.println("2����Կ");	
			int t2 = cin.nextInt();
			if( t2 == 1 ){
				System.out.println("������Ҫ���ܵ��ļ���ַ��������ɺ��ļ���ɾ������");
				String s1 = cin.next();
				System.out.println("����������ļ������ַ��");
				String s2 = cin.next();
				encodeanddecodemain mode1 = new encodeanddecode1();	
				mode1.performDecode(s1, s2);
			}
			else if(t2 == 2) {
				System.out.println("��������Կ��");
				String s = cin.next();
				encodeanddecodemain mode2 = new encodeanddecode2(s);
				System.out.println("������Ҫ���ܵ��ļ���ַ��������ɺ��ļ���ɾ������");
				String s1 = cin.next();
				System.out.println("����������ļ������ַ��");
				String s2 = cin.next();
				mode2.performDecode(s1, s2);
				
			}
			else {
				System.out.println("��������Ч���ַ���");
			}
		}
		else {
			System.out.println("��������Ч���ַ���");
		}

		cin.close();
	}


}
