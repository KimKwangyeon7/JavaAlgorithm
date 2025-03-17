
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
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] tmp = new int[N][M];
		int max = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				tmp[i][j] = str.charAt(j) - '0';
				max = Math.max(max, tmp[i][j]);
			}
		}
		boolean[][][]board = new boolean[max][N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < tmp[i][j]; k++) {
					board[k][i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < max; i++) {
			answer += bfs(board, i, N, M);
		}
		System.out.println(answer);
	}
	private static int bfs(boolean[][][] board, int h, int N, int M) {
		boolean[][] visited= new boolean[N][M];
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!board[h][i][j] && !visited[i][j]) {
					Queue<int[]> qu = new ArrayDeque<>();
					visited[i][j] = true;
					qu.offer(new int[] {i, j});
					int cnt = 0;
					boolean flag = false;
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						cnt++;
						
						for (int k = 0; k < 4; k++) {
							int nx = tmp[0] + dx[k];
							int ny = tmp[1] + dy[k];
							
							if (!isBoundary(nx, ny, N, M)) {
								flag = true;
								continue;
							}
							if (visited[nx][ny] || board[h][nx][ny]) {
								continue;
							}
							visited[nx][ny] = true;
							qu.offer(new int[] {nx, ny});
						}
					}
					if (flag) {
						cnt = 0;
					}
					total += cnt;
				}
			}
		}
		return total;
	}
	private static boolean isBoundary(int x, int y, int N, int M) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
