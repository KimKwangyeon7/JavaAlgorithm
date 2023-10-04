
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Main {
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 2;
		Queue<int[]> qu = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					board[i][j] = cnt;
					qu.offer(new int[] {i, j});
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						for (int k = 0; k < 4; k++) {
							int x = tmp[0] + dx[k];
							int y = tmp[1] + dy[k];
							
							if (!isBoundary(x, y) || board[x][y] != 1) {
								continue;
							}
							board[x][y] = cnt;
							qu.offer(new int[] {x, y});
						}
					}
					cnt++;
					qu.clear();
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
		int t = 0;
		int ans = 2147000000;
		boolean[][] visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) {
					visit = new boolean[N][N];
					t = board[i][j];
					visit[i][j] = true;
					qu.offer(new int[] {i, j, 0});
					int flag = 0;
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						for (int k = 0; k < 4; k++) {
							int x = tmp[0] + dx[k];
							int y = tmp[1] + dy[k];
							
							if (!isBoundary(x, y) || board[x][y] == t || visit[x][y]) {
								continue;
							}
							if (board[x][y] != 0) {
								ans = Math.min(ans, tmp[2]+1);
								flag = 1;
								break;
							}
							visit[x][y] = true;
							qu.offer(new int[] {x, y, tmp[2]+1});
						}
						if (flag == 1) {
							break;
						}
					}
					qu.clear();
				}
			}
		}
		System.out.println(ans-1);
	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
