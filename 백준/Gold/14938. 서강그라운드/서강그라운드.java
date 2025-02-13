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
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[] item = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n+1; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		int[][] board = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(board[i], INF);
			board[i][i] = 0;
		}
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[a][b] = c;
			board[b][a] = c;
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (board[i][k] != INF && board[k][j] != INF) {
						board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
					}
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (board[i][j] <= m) {
					sum += item[j];
				}
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
