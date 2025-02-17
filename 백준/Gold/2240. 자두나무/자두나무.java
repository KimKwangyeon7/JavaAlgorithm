
import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] board = new int[T+1];
        int[][] dp = new int[T+1][W+1];
        for (int i = 1; i <= T; i++) {
        	board[i] = Integer.parseInt(br.readLine());
        }
        if (board[1] == 1) {
        	dp[1][0] = 1;
        	dp[1][1] = 0;
        } else {
        	dp[1][0] = 0;
        	dp[1][1] = 1;
        }
        int max = 1;
        for (int i = 2; i <= T; i++) {
        	for (int j = 0; j <= i && j <= W; j++) {
        		if (j == 0) {
        			dp[i][j] = dp[i-1][j];
        			if ((j%2)+1 == board[i]) {
        				dp[i][j] += 1;
        			}
        		} else {
        			int tmp1 = dp[i-1][j];
        			int tmp2 = dp[i-1][j-1];
        			if ((j%2)+1 == board[i]) {
        				tmp1 += 1;
        			} 
        			if (((j+1)%2)+1 == board[i]) {
        				tmp2 += 1;
        			}
        			dp[i][j] = Math.max(tmp1, tmp2);
        		}
        		max = Math.max(dp[i][j], max);
        	}
        }
        System.out.println(max);
	}
}
