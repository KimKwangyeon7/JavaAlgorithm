
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_16234_인구이동_김광연
 * 메모리
 * 아이디어
 * 
 */
public class Main {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> qu = new ArrayDeque<>();
		Queue<int[]> cand = new ArrayDeque<>();
		int day = 0;
		int flag = 0;
		while (true) {
			visit = new boolean[N][N];
			flag = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						qu.offer(new int[] {i, j});
						cand.offer(new int[] {i, j});
						visit[i][j] = true;
						int sum = board[i][j];
						int cnt = 1;
						while (!qu.isEmpty()) {
							int[] tmp = qu.poll();
							for (int k = 0; k < 4; k++) { // 상하좌우 탐색
								int x =tmp[0] + dx[k];
								int y = tmp[1] + dy[k];
								
								if (!isBoundary(x, y) || visit[x][y]) { // 범위에서 벗어나거나 이미 방문했으면 패스
									continue;
								}
								// 두 값의 차가 L과 R 사이에 들면
								if (Math.abs(board[tmp[0]][tmp[1]] - board[x][y]) >= L && Math.abs(board[tmp[0]][tmp[1]] - board[x][y]) <= R) {
									qu.offer(new int[] {x, y});
									cand.offer(new int[] {x, y}); 
									cnt++;
									sum += board[x][y];
									visit[x][y] = true;
								}
						
							}
						}
						if (cand.size() > 1) {
							flag = 1;
							for (int[] tmp: cand) {
								board[tmp[0]][tmp[1]] = sum / cnt;
							}
						}
						cand.clear();
					}
				}
			}
			if (flag == 0) {
				break;
			} else {
				day++;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(day);
	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x< N && y >= 0 && y < N;
	}

}
