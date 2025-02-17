

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
	static int[][] board;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < m; j++) {
        		board[i][j] = str.charAt(j) - '0';
        		dp[i][j] = board[i][j];
        	}
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		if (board[i][j] == 1 && i > 0 && j > 0) {
        			dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
        		}
        		max = Math.max(dp[i][j], max);
        	}
        }
        System.out.println(max*max);
	}
}
