
import java.util.Scanner;

public class bashuma {
	static int[][] arr2 = new int[3][3];//= { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
	static int aaa;		//���ڼ�¼�ƶ�����
	static int bbb=10;	//���ڼ�¼���

	public static void main(String[] args) {
		int[][] arr1 = new int[3][3];	//�����ά����
		System.out.println("�������������飨�ո���0���棩���������룩��");
		Scanner input = new Scanner(System.in);		//�����������뺯��
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int ss =input.nextInt();
				arr2[i][j] = ss; 
			}
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("����״̬��");
		shuchu(arr2);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("������Ҫ�жϵ����飨�ո���0���棩���������룩��");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int ss =input.nextInt();
				arr1[i][j] = ss; 
			}
		}
		System.out.println("����������飺");
		shuchu(arr1);
		System.out.println(" ");
		yidong(arr1, 1, 0);	//�����ƶ�����
		System.out.println("..................................................");
		System.out.println("û���ҵ�");
		input.close();
	}

	public static void shuchu(int a[][]) { // �����ά����

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean panduan(int a[][], int b[][]) { // �ж�������ά����������Ƿ����
		boolean esta = true;

		for (int i = 0; i < a.length && esta == true; i++) {
			for (int j = 0; j < a[i].length && esta == true; j++) {
				if (a[i][j] != b[i][j]) {
					esta = false;

				}
			}
			
		}
		return esta;
	}
	public static void jieshu() { // ��������
		aaa++;	//�ƶ�����
		System.out.println("jieshu");
		System.out.println("������" + aaa +"��" );
		System.exit(0);
	}

	public static int[][] zuoyi(int a2[][]) { // ��ԭ���ƺ���
	
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// �ƶ������ڵ���
					n2 = j;// �ƶ������ڵ���
				}
			}
		}
		int t1 = a1[n1][n2 + 1];
		a1[n1][n2 + 1] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] shangyi(int a2[][]) { // ��ԭ���ƺ���
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// �ƶ������ڵ���
					n2 = j;// �ƶ������ڵ���
				}
			}
		}
		int t1 = a1[n1 + 1][n2];
		a1[n1 + 1][n2] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] youyi(int a2[][]) { // ��ԭ���ƺ���
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// �ƶ������ڵ���
					n2 = j;// �ƶ������ڵ���
				}
			}
		}
		int t1 = a1[n1][n2 - 1];
		a1[n1][n2 - 1] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] xiayi(int a2[][]) { // ��ԭ���ƺ���
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					n1 = i;// �ƶ������ڵ���
					n2 = j;// �ƶ������ڵ���
				}
			}
		}
		int t1 = a1[n1 - 1][n2];
		a1[n1 - 1][n2] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static void yidong(int a[][], int s, int x) {

		aaa++;	//�ƶ�����
		int[][] ar2 = a;	//������������
		int q = s;		//���
		int c=x;		//�����жϲ������ĸ������ƶ���1���������� 2���������� 3���������� 4���������ƣ�
		System.out.println("��"+ aaa +"��"+ ":");
		System.out.println("��ȣ� " + q);
		shuchu(ar2);
		if (q == bbb) {
			System.out.println("*********************************");
			System.out.println("�����趨�����");
			System.out.println("*********************************");
		} else {
			int num1 = -1;
			int num2 = -1;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (a[i][j] == 0) {

						num1 = i;// �ƶ������ڵ���
						num2 = j;// �ƶ������ڵ���
					}
				}
			}
			int pan = c;
			int o = num2 - 1;
			// System.out.println(o);
			if (o < 0 || pan == 1) {		//����
				System.out.println("");
			} else {
				
				System.out.println("���ƣ�");
				pan = 3;
				int t1 = ar2[num1][num2 - 1];
				ar2[num1][num2 - 1] = ar2[num1][num2];
				ar2[num1][num2] = t1;
				boolean aa = panduan(ar2, arr2);
				if (aa == true) {
					System.out.println("right");
					jieshu();
				}
				else {
					yidong(ar2,q+1,pan);
					pan=c;
					ar2=zuoyi(ar2);		//�������ƻ�ԭ����
					System.out.println("��ԭ��");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num1 - 1 < 0 || pan == 2) {		//����
				System.out.println("");
			} else {
				
				System.out.println("���ƣ�");
				pan = 4;
				int t1 = ar2[num1 - 1][num2];
				ar2[num1 - 1][num2] = ar2[num1][num2];
				ar2[num1][num2] = t1;
				boolean aa = panduan(ar2, arr2);
				if (aa == true) {
					System.out.println("right");
					jieshu();
				}
				else {
					yidong(ar2,q+1,pan);
					pan=c;
					ar2=shangyi(ar2);		//�������ƻ�ԭ����
					System.out.println("��ԭ��");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num2 + 1 > 2 || pan == 3) {		//����
				System.out.println("");
			} else {
				
				System.out.println("���ƣ�");
				pan = 1;
				int t1 = ar2[num1][num2 + 1];
				ar2[num1][num2 + 1] = ar2[num1][num2];
				ar2[num1][num2] = t1;
				boolean aa = panduan(ar2, arr2);
				if (aa == true) {
					System.out.println("right");
					jieshu();
				}
				else {
					yidong(ar2,q+1,pan);
					pan=c;
					ar2=youyi(ar2);		//�������ƻ�ԭ����
					System.out.println("��ԭ��");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num1 + 1 > 2 || pan == 4) {		//����
				System.out.println("");;
			} else {
				
				System.out.println("���ƣ�");
				pan = 2;
				int t1 = ar2[num1 + 1][num2];
				ar2[num1 + 1][num2] = ar2[num1][num2];
				ar2[num1][num2] = t1;
				boolean aa = panduan(ar2, arr2);
				if (aa == true) {
					System.out.println("right");
					jieshu();
				}
				else {
					yidong(ar2,q+1,pan);
					pan=c;
					ar2=xiayi(ar2);		//�������ƻ�ԭ����
					System.out.println("��ԭ��");
					shuchu(ar2);
					System.out.println(" ");
				}
			
			}

		}
	}
}

