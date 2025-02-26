

import java.util.*;
import java.io.*;
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
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[N+1][N+1];
			int[] board = new int[N+1];
			int[] sum = new int[N+1];
	
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N+1; i++) {
				board[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + board[i];
			}

			for (int k = 2; k <= N; k++) {
				for (int i = 1; i <= N-k+1; i++) {
                    dp[i][i+k-1] = Integer.MAX_VALUE;
					for (int j = i; j < i+k-1; j++) {
						dp[i][i+k-1] = Math.min(dp[i][i+k-1], dp[i][j] + dp[j+1][i+k-1] + sum[i+k-1] - sum[i - 1]);
					}
				}
			}
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= N; j++) {
//					System.out.print(dp[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			T--;
			sb.append(dp[1][N]).append("\n");
		}
		System.out.println(sb);
	}
}
