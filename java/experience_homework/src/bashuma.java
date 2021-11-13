
import java.util.Scanner;

public class bashuma {
	static int[][] arr2 = new int[3][3];//= { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
	static int aaa;		//用于记录移动步数
	static int bbb=10;	//用于记录深度

	public static void main(String[] args) {
		int[][] arr1 = new int[3][3];	//定义二维数组
		System.out.println("请输入最终数组（空格用0代替）（按行输入）：");
		Scanner input = new Scanner(System.in);		//创建键盘输入函数
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int ss =input.nextInt();
				arr2[i][j] = ss; 
			}
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("最终状态：");
		shuchu(arr2);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("请输入要判断的数组（空格用0代替）（按行输入）：");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int ss =input.nextInt();
				arr1[i][j] = ss; 
			}
		}
		System.out.println("你输入的数组：");
		shuchu(arr1);
		System.out.println(" ");
		yidong(arr1, 1, 0);	//调用移动函数
		System.out.println("..................................................");
		System.out.println("没有找到");
		input.close();
	}

	public static void shuchu(int a[][]) { // 输出二维数组

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean panduan(int a[][], int b[][]) { // 判断两个二维数组的内容是否相等
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
	public static void jieshu() { // 结束函数
		aaa++;	//移动步数
		System.out.println("jieshu");
		System.out.println("共用了" + aaa +"步" );
		System.exit(0);
	}

	public static int[][] zuoyi(int a2[][]) { // 还原左移函数
	
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// 移动格所在的行
					n2 = j;// 移动格所在的列
				}
			}
		}
		int t1 = a1[n1][n2 + 1];
		a1[n1][n2 + 1] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] shangyi(int a2[][]) { // 还原上移函数
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// 移动格所在的行
					n2 = j;// 移动格所在的列
				}
			}
		}
		int t1 = a1[n1 + 1][n2];
		a1[n1 + 1][n2] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] youyi(int a2[][]) { // 还原右移函数
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					
					n1 = i;// 移动格所在的行
					n2 = j;// 移动格所在的列
				}
			}
		}
		int t1 = a1[n1][n2 - 1];
		a1[n1][n2 - 1] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static int[][] xiayi(int a2[][]) { // 还原下移函数
		int[][] a1 = a2;
		int n1=0;
		int n2=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (a1[i][j] == 0) {
					n1 = i;// 移动格所在的行
					n2 = j;// 移动格所在的列
				}
			}
		}
		int t1 = a1[n1 - 1][n2];
		a1[n1 - 1][n2] = a1[n1][n2];
		a1[n1][n2] = t1;
		return a1;
	}
	public static void yidong(int a[][], int s, int x) {

		aaa++;	//移动步数
		int[][] ar2 = a;	//传过来的数组
		int q = s;		//深度
		int c=x;		//用来判断不能向哪个方向移动（1，不能右移 2，不能下移 3，不能左移 4，不能上移）
		System.out.println("第"+ aaa +"步"+ ":");
		System.out.println("深度： " + q);
		shuchu(ar2);
		if (q == bbb) {
			System.out.println("*********************************");
			System.out.println("到达设定的深度");
			System.out.println("*********************************");
		} else {
			int num1 = -1;
			int num2 = -1;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (a[i][j] == 0) {

						num1 = i;// 移动格所在的行
						num2 = j;// 移动格所在的列
					}
				}
			}
			int pan = c;
			int o = num2 - 1;
			// System.out.println(o);
			if (o < 0 || pan == 1) {		//左移
				System.out.println("");
			} else {
				
				System.out.println("左移：");
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
					ar2=zuoyi(ar2);		//调用左移还原函数
					System.out.println("还原：");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num1 - 1 < 0 || pan == 2) {		//上移
				System.out.println("");
			} else {
				
				System.out.println("上移：");
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
					ar2=shangyi(ar2);		//调用上移还原函数
					System.out.println("还原：");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num2 + 1 > 2 || pan == 3) {		//右移
				System.out.println("");
			} else {
				
				System.out.println("右移：");
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
					ar2=youyi(ar2);		//调用右移还原函数
					System.out.println("还原：");
					shuchu(ar2);
					System.out.println(" ");
				}
			}
			if (num1 + 1 > 2 || pan == 4) {		//下移
				System.out.println("");;
			} else {
				
				System.out.println("下移：");
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
					ar2=xiayi(ar2);		//调用下移还原函数
					System.out.println("还原：");
					shuchu(ar2);
					System.out.println(" ");
				}
			
			}

		}
	}
}

