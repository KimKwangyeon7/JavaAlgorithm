

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
        int n = Integer.parseInt(br.readLine());
        int[] board = new int[n+1];
        for (int i = 1; i <= n; i++) {
        	board[i] = Integer.parseInt(br.readLine());
        }
        int[][] dp = new int[n+1][3];
        if (n == 1) {
        	System.out.println(board[1]);
        } else if (n == 2) {
        	System.out.println(board[1] + board[2]);
        } else {
        	dp[1][0] = 0;
            dp[1][1] = board[1];
            dp[2][0] = board[1];
            dp[2][1] = board[2];
            dp[2][2] = board[2]+board[1];
            
            int max = 0;
            for (int i = 3; i <= n; i++) {
            	for (int j = 0; j < 3; j++) {
            		if (j == 0) {
            			dp[i][j] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            		} else {
            			dp[i][j] = dp[i-1][j-1] + board[i];
            		}
            		max = Math.max(max, dp[i][j]);
            	}
            }
            System.out.println(max);
        }
	}
}
