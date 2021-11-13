
import java.util.Scanner;

public class Stones_Combine {

	//求i到j的总和 
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
        //第一行输入石子堆数n,第二行输入每堆石子的数量,用空格分开
    	System.out.println("请输入");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] stones = new int[n * 2];
        for (int i = 0; i < n; i++) {
            stones[i] = in.nextInt();
        }
        //将N个石堆复制一份为2N堆，化环为直，如1，2，3的环转化为1，2，3，1，2，3的直线
        for (int i = n; i < n * 2; i++) {
            stones[i] = stones[i - n];
        }
        System.out.println(dpOptimization(stones));
        System.out.println(dpDegradation(stones));
        in.close();
    }
	
    //直线
//	public static int dpMethoddown(int[] stones, int i, int j) {
//        if (i == j) {
//            return 0;
//        }else {
//            int min = Integer.MAX_VALUE;
//            for (int k = i; k <j; k++) {
//                //递归求解
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
//                //递归求解
//                int tmp = dpMethodup(stones, i, k) + dpMethodup(stones, k + 1, j) + dpSum(stones, i, j);
//                if (max < tmp)
//                    max = tmp;
//            }
//            return max;
//        }
//    }
//
//	//求i到j的总和 
//    public static int dpSum(int[] stones, int i, int j) {
//        int sum = 0;
//        for (int k = i ; k <= j; k++) {
//            sum += stones[k];
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//    	System.out.println("请输入：");
//        //第一行输入石子堆数n,第二行输入每堆石子的数量,用空格分开
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
