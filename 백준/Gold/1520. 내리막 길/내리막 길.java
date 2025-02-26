
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
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {1, -1, 0, 0};
	static int[][] board;
	static int[][] dp;
	static int M, N;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
        	Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0));
	}
	private static int dfs(int x, int y) {
		if (x == N-1 && y == M-1) {
			return 1;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (isBoundary(nx, ny) && board[nx][ny] < board[x][y]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
