

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
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N];
        for (int i = 0; i < N; i++) {
        	board[i] = Integer.parseInt(br.readLine());
        }
        
        int[] dp = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
        	dp[i] = 1;
        	for (int j = 0; j < i; j++) {
        		if (board[j] < board[i]) {
        			dp[i] = Math.max(dp[i], dp[j]+1);
        		}
        	}
        	max = Math.max(dp[i], max);
        }
        System.out.println(N-max);
	}

}
