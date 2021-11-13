package strassen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class strassen {
 
	public static void main(String[] args){
		
		System.out.print("����������ά����");
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		
		Random random = new Random();
		int[][] A = new int[n][n];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				A[i][j] = random.nextInt(5) + 1;
			}
		}
		try {
			File file = new File("matriaA.txt");  //����������ݵ��ļ�
			FileWriter out = new FileWriter(file);  //�ļ�д����

			//�������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					out.write(A[i][j]+"\t");
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
    
		
		int[][] B = new int[n][n];
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[i].length; j++) {
				B[i][j] = random.nextInt(5) + 1;
			}
		}
		try {
			File file = new File("matriaA.txt");  //����������ݵ��ļ�
			FileWriter out = new FileWriter(file);  //�ļ�д����

			//�������е�����д�뵽�ļ��С�ÿ�и�����֮��TAB���
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					out.write(A[i][j]+"\t");
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println();
		
		long start = System.nanoTime();
		System.out.println("ʹ�ñ���������ʽ�ķ���������");
		int[][] C = martixMultiplyRecursive(A, B);
		displaySquare(C);
		long end = System.nanoTime();
		System.out.println("�����ƽ�ķ�" + (end - start)/1000 + "΢��");
		System.out.println();
 
		start = System.nanoTime();
		System.out.println("ʹ�÷���˼�����ͨ��ʽ�ķ���������");
		int[][] C1 = martixMultiplyRecursive(A, B);
		displaySquare(C1);
		end = System.nanoTime();
		System.out.println("��ͨ�����ƽ�ķ�" + (end - start)/1000 + "΢��");
		System.out.println();
 
		start = System.nanoTime();
		System.out.println("Strassen �������");
		int[][] C2 = strassenMartixMultiplyRecursive(A, B);
		displaySquare(C2);
		end = System.nanoTime();
		System.out.println("Strassen�ƽ�ķ�" + (end - start)/1000 + "΢��");
		System.out.println();
		
		cin.close();
	}
 
	//	��������˷�����
	//	  A	�μ�����ľ���֮һA
	//	  B	�μ�����ľ���֮һB
	
	public static int[][] squareMatrixMultiply(int[][] A, int[][] B) {
 
		int rows = A.length;
		int[][] C = new int[rows][rows];
 
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				C[i][j] = 0;
				for (int k = 0; k < rows; k++) {
					C[i][j] = C[i][j] + A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}
 
	//	ʹ�÷���˼���NxN����˷�����
	//	  A	�μ�����ľ���֮һA
	//	  B	�μ�����ľ���֮һB
	
	public static int[][] martixMultiplyRecursive(int[][] A, int[][] B) {
		int rows = A.length;
		int[][] C = new int[rows][rows];
		if (rows == 1) {
			C[0][0] = A[0][0] * B[0][0];
		} else {
			int[][] A11 = new int[rows / 2][rows / 2];
			int[][] A12 = new int[rows / 2][rows / 2];
			int[][] A21 = new int[rows / 2][rows / 2];
			int[][] A22 = new int[rows / 2][rows / 2];
 
			copyMatrixbyParamFromSrcToSubMatrix(A, 0, rows / 2, 0, rows / 2, A11);
			copyMatrixbyParamFromSrcToSubMatrix(A, 0, rows / 2, rows / 2, rows / 2, A12);
			copyMatrixbyParamFromSrcToSubMatrix(A, rows / 2, rows / 2, 0, rows / 2, A21);
			copyMatrixbyParamFromSrcToSubMatrix(A, rows / 2, rows / 2, rows / 2, rows / 2, A22);
 
			int[][] B11 = new int[rows / 2][rows / 2];
			int[][] B12 = new int[rows / 2][rows / 2];
			int[][] B21 = new int[rows / 2][rows / 2];
			int[][] B22 = new int[rows / 2][rows / 2];
 
			copyMatrixbyParamFromSrcToSubMatrix(B, 0, rows / 2, 0, rows / 2, B11);
			copyMatrixbyParamFromSrcToSubMatrix(B, 0, rows / 2, rows / 2, rows / 2, B12);
			copyMatrixbyParamFromSrcToSubMatrix(B, rows / 2, rows / 2, 0, rows / 2, B21);
			copyMatrixbyParamFromSrcToSubMatrix(B, rows / 2, rows / 2, rows / 2, rows / 2, B22);
 
			int[][] C11 = new int[rows / 2][rows / 2];
			int[][] C12 = new int[rows / 2][rows / 2];
			int[][] C21 = new int[rows / 2][rows / 2];
			int[][] C22 = new int[rows / 2][rows / 2];
 
			squareMatrixElementAdd(squareMatrixMultiply(A11, B11), squareMatrixMultiply(A12, B21), C11);
			squareMatrixElementAdd(squareMatrixMultiply(A11, B12), squareMatrixMultiply(A12, B22), C12);
			squareMatrixElementAdd(squareMatrixMultiply(A21, B11), squareMatrixMultiply(A22, B21), C21);
			squareMatrixElementAdd(squareMatrixMultiply(A21, B12), squareMatrixMultiply(A22, B22), C22);
 
			// ��C11/C12/C21/C22�ĸ��Ӿ���ϲ�Ϊ���յĽ��C����
			copySubMatrixByParamFromSrcToDest(C11, 0, rows / 2, 0, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C12, 0, rows / 2, rows / 2, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C21, rows / 2, rows / 2, 0, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C22, rows / 2, rows / 2, rows / 2, rows / 2, C);
 
		}
		return C;
	}
 
	//	Strassen�㷨��NxN����˷�����
	//	  A	�μ�����ľ���֮һA
	//	  B	�μ�����ľ���֮һB

	public static int[][] strassenMartixMultiplyRecursive(int[][] A, int[][] B) {
		int rows = A.length;
		int[][] C = new int[rows][rows];
		if (rows == 1) {
			C[0][0] = A[0][0] * B[0][0];
		} else {
			int[][] A11 = new int[rows / 2][rows / 2];
			int[][] A12 = new int[rows / 2][rows / 2];
			int[][] A21 = new int[rows / 2][rows / 2];
			int[][] A22 = new int[rows / 2][rows / 2];
 
			copyMatrixbyParamFromSrcToSubMatrix(A, 0, rows / 2, 0, rows / 2, A11);
			copyMatrixbyParamFromSrcToSubMatrix(A, 0, rows / 2, rows / 2, rows / 2, A12);
			copyMatrixbyParamFromSrcToSubMatrix(A, rows / 2, rows / 2, 0, rows / 2, A21);
			copyMatrixbyParamFromSrcToSubMatrix(A, rows / 2, rows / 2, rows / 2, rows / 2, A22);
 
			int[][] B11 = new int[rows / 2][rows / 2];
			int[][] B12 = new int[rows / 2][rows / 2];
			int[][] B21 = new int[rows / 2][rows / 2];
			int[][] B22 = new int[rows / 2][rows / 2];
 
			copyMatrixbyParamFromSrcToSubMatrix(B, 0, rows / 2, 0, rows / 2, B11);
			copyMatrixbyParamFromSrcToSubMatrix(B, 0, rows / 2, rows / 2, rows / 2, B12);
			copyMatrixbyParamFromSrcToSubMatrix(B, rows / 2, rows / 2, 0, rows / 2, B21);
			copyMatrixbyParamFromSrcToSubMatrix(B, rows / 2, rows / 2, rows / 2, rows / 2, B22);
 
			int[][] S1 = new int[rows / 2][rows / 2];
			int[][] S2 = new int[rows / 2][rows / 2];
			int[][] S3 = new int[rows / 2][rows / 2];
			int[][] S4 = new int[rows / 2][rows / 2];
			int[][] S5 = new int[rows / 2][rows / 2];
			int[][] S6 = new int[rows / 2][rows / 2];
			int[][] S7 = new int[rows / 2][rows / 2];
			int[][] S8 = new int[rows / 2][rows / 2];
			int[][] S9 = new int[rows / 2][rows / 2];
			int[][] S10 = new int[rows / 2][rows / 2];
 
			squareMatrixElementSub(B12, B22, S1);// S1 = B12 - B22
			squareMatrixElementAdd(A11, A12, S2);// S2 = A11 + A12
			squareMatrixElementAdd(A21, A22, S3);// S3 = A21 + A22
			squareMatrixElementSub(B21, B11, S4);// S4 = B21 - B11
			squareMatrixElementAdd(A11, A22, S5);// S5 = A11 + A22
			squareMatrixElementAdd(B11, B22, S6);// S6 = B11 + B22
			squareMatrixElementSub(A12, A22, S7);// S7 = A12 - A22
			squareMatrixElementAdd(B21, B22, S8);// S8 = B21 + B22
			squareMatrixElementSub(A11, A21, S9);// S9 = A11 - A21
			squareMatrixElementAdd(B11, B12, S10);// S10 = B11 + B12
 
			int[][] P1 = new int[rows / 2][rows / 2];
			int[][] P2 = new int[rows / 2][rows / 2];
			int[][] P3 = new int[rows / 2][rows / 2];
			int[][] P4 = new int[rows / 2][rows / 2];
			int[][] P5 = new int[rows / 2][rows / 2];
			int[][] P6 = new int[rows / 2][rows / 2];
			int[][] P7 = new int[rows / 2][rows / 2];
 
			P1 = strassenMartixMultiplyRecursive(A11, S1); // P1 = A11 X S1
			P2 = strassenMartixMultiplyRecursive(S2, B22);// P2 = S2 X B22
			P3 = strassenMartixMultiplyRecursive(S3, B11);// P3 = S3 X B11
			P4 = strassenMartixMultiplyRecursive(A22, S4);// P4 = A22 X S4
			P5 = strassenMartixMultiplyRecursive(S5, S6);// P5 = S5 X S6
			P6 = strassenMartixMultiplyRecursive(S7, S8);// P6 = S7 X S8
			P7 = strassenMartixMultiplyRecursive(S9, S10);// P7 = S9 X S10
 
			int[][] C11 = new int[rows / 2][rows / 2];
			int[][] C12 = new int[rows / 2][rows / 2];
			int[][] C21 = new int[rows / 2][rows / 2];
			int[][] C22 = new int[rows / 2][rows / 2];
 
			int[][] temp = new int[rows / 2][rows / 2];
 
			// C11 = P5 + P4 - P2 + P6
			squareMatrixElementAdd(P5, P4, temp);
			squareMatrixElementSub(temp, P2, temp);
			squareMatrixElementAdd(temp, P6, C11);
 
			// C12 = P1 + P2
			squareMatrixElementAdd(P1, P2, C12);
 
			// C21 = P3 + P4
			squareMatrixElementAdd(P3, P4, C21);
 
			// C22 = P5 + P1 - P3 -P7
			squareMatrixElementAdd(P5, P1, temp);
			squareMatrixElementSub(temp, P3, temp);
			squareMatrixElementSub(temp, P7, C22);
 
			// ��C11/C12/C21/C22�ĸ��Ӿ���ϲ�Ϊ���յĽ��C����
			copySubMatrixByParamFromSrcToDest(C11, 0, rows / 2, 0, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C12, 0, rows / 2, rows / 2, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C21, rows / 2, rows / 2, 0, rows / 2, C);
			copySubMatrixByParamFromSrcToDest(C22, rows / 2, rows / 2, rows / 2, rows / 2, C);
 
		}
		return C;
	}
 
	// ��һ��NxN�Ĵ����ֽ��4��N/2xN/2���Ӿ���

	public static void copyMatrixbyParamFromSrcToSubMatrix(int[][] src, int startI, int lenI, int startJ, int lenJ,
			int[][] dest) {
		for (int i = 0; i < lenI; i++)
			for (int j = 0; j < lenJ; j++) {
				dest[i][j] = src[startI + i][startJ + j];
			}
	}
 
	// ��4��N/2xN/2���Ӿ���ϲ���һ��NxN�Ĵ����
	 
	public static void copySubMatrixByParamFromSrcToDest(int[][] src, int startI, int lenI, int startJ, int lenJ,
			int[][] dest) {
		for (int i = 0; i < lenI; i++)
			for (int j = 0; j < lenJ; j++) {
				dest[startI + i][startJ + j] = src[i][j];
			}
	}
 
	//	NxN����ӷ�
	//	  srcA	�ӷ�Դ����֮һ
	//	  srcB	�ӷ�Դ����֮��
	//	  dest	����ӷ����

	public static void squareMatrixElementAdd(int[][] srcA, int[][] srcB, int[][] dest) {
		for (int i = 0; i < srcA.length; i++)
			for (int j = 0; j < srcA[i].length; j++)
				dest[i][j] = srcA[i][j] + srcB[i][j];
	}
 
	//	����
	
	public static void squareMatrixElementSub(int[][] srcA, int[][] srcB, int[][] dest) {
		for (int i = 0; i < srcA.length; i++)
			for (int j = 0; j < srcA[i].length; j++)
				dest[i][j] = srcA[i][j] - srcB[i][j]; 
		
		
	}
 
	//	��ӡ����
	
	public static void displaySquare(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j : matrix[i]) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}
}
