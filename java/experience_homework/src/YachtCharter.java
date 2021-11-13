import java.io.FileInputStream;
import java.util.Scanner;

public class YachtCharter {
	
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n;
        int[][] fee;
        try{
			in=new Scanner(new FileInputStream("input.txt"));
			}catch(Exception e){
				
			}
        while (in.hasNext()){
            n = in.nextInt();
            fee = new int[n][n];

            for(int row=0; row<n-1; row++)
                for(int col=row+1; col<n; col++)
                    fee[row][col] = in.nextInt();

            for(int k=2; k<n; k++)
                for(int i=0; i<n-k; i++){
                    int j = i+k;
                    for(int p =i+1; p<j; p++){
                        int tmp = fee[i][p] + fee[p][j];
                        if(fee[i][j] > tmp)
                            fee[i][j] = tmp;
                    }
                }

            System.out.println(fee[0][n-1]);
            //fee[i][j]: 从第i个出租站到第j个出租站所需的最少租金，可见fee[0][n-1]即为所求
        }
    }


}