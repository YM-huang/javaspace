
import java.util.Scanner;

public class Stones_Combine {

	//��i��j���ܺ� 
    public static int dpSum(int[] stones, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += stones[k];
        }
        return sum;
    }

    public static int dpOptimization(int[] stones) {
        int n = stones.length;
        int[][] dp = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            s[i][i] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int tmp = Integer.MAX_VALUE;
                int fence = 0;
                for (int k = s[i][j - 1]; k <= s[i + 1][j]; k++) {
                    int sum = dp[i][k] + dp[k + 1][j] + dpSum(stones, i, j);
                    if (tmp > sum) {
                        tmp = sum;
                        fence = k;
                    }
                }
                dp[i][j] = tmp;
                s[i][j] = fence;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n / 2; i++) {
            if (min > dp[i][i + (n / 2 - 1)]) {
                min = dp[i][i + (n / 2 - 1)];
            }
        }
        return min;
    }
    
    public static int dpDegradation(int[] stones) {
    	int n = stones.length;
        int[][] dp = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            s[i][i] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int tmp = Integer.MIN_VALUE;
                int fence = 0;
                for (int k = s[i][j - 1]; k <= s[i + 1][j]; k++) {
                    int sum = dp[i][k] + dp[k + 1][j] + dpSum(stones, i, j);
                    if (tmp < sum) {
                        tmp = sum;
                        fence = k;
                    }
                }
                dp[i][j] = tmp;
                s[i][j] = fence;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n / 2; i++) {
            if (max < dp[i][i + (n / 2 - 1)]) {
                max = dp[i][i + (n / 2 - 1)];
            }
        }
    	return max;
    }

    public static void main(String[] args) {
        //��һ������ʯ�Ӷ���n,�ڶ�������ÿ��ʯ�ӵ�����,�ÿո�ֿ�
    	System.out.println("������");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] stones = new int[n * 2];
        for (int i = 0; i < n; i++) {
            stones[i] = in.nextInt();
        }
        //��N��ʯ�Ѹ���һ��Ϊ2N�ѣ�����Ϊֱ����1��2��3�Ļ�ת��Ϊ1��2��3��1��2��3��ֱ��
        for (int i = n; i < n * 2; i++) {
            stones[i] = stones[i - n];
        }
        System.out.println(dpOptimization(stones));
        System.out.println(dpDegradation(stones));
        in.close();
    }
	
    //ֱ��
//	public static int dpMethoddown(int[] stones, int i, int j) {
//        if (i == j) {
//            return 0;
//        }else {
//            int min = Integer.MAX_VALUE;
//            for (int k = i; k <j; k++) {
//                //�ݹ����
//                int tmp = dpMethoddown(stones, i, k) + dpMethoddown(stones, k + 1, j) + dpSum(stones, i, j);
//                if (min > tmp)
//                    min = tmp;
//            }
//            return min;
//        }
//    }
//	
//	public static int dpMethodup(int[] stones, int i, int j) {
//        if (i == j) {
//            return 0;
//        }else {
//            int max = Integer.MIN_VALUE;
//            for (int k = i; k <j; k++) {
//                //�ݹ����
//                int tmp = dpMethodup(stones, i, k) + dpMethodup(stones, k + 1, j) + dpSum(stones, i, j);
//                if (max < tmp)
//                    max = tmp;
//            }
//            return max;
//        }
//    }
//
//	//��i��j���ܺ� 
//    public static int dpSum(int[] stones, int i, int j) {
//        int sum = 0;
//        for (int k = i ; k <= j; k++) {
//            sum += stones[k];
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//    	System.out.println("�����룺");
//        //��һ������ʯ�Ӷ���n,�ڶ�������ÿ��ʯ�ӵ�����,�ÿո�ֿ�
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] stones = new int[n];
//        for (int i = 0; i < n; i++) {
//            stones[i] = in.nextInt();
//        }
//        System.out.println(dpMethoddown(stones, 0, n - 1));
//        System.out.println(dpMethodup(stones, 0, n - 1));
//        in.close();
//    }
}
