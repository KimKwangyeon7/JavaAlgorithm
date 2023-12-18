import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로칸 수
		N = Integer.parseInt(st.nextToken()); // 세로칸 수
		int[][] board = new int[N][M]; // 토마토들
		int none = 0; // 토마토가 없는 빈칸
		int ripen = 0; // 익은 토마토
		Queue<int[]> que = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == -1) { // 토마토가 없는 곳이면
					none++;
				}
				if (board[i][j] == 1) { // 이미 익은 토마토면
					ripen++;
					que.offer(new int[] {i, j, 0}); // 좌표와 시간을 큐에 넣기
				}
			}
		}
		
		if (ripen == (N*M - none)) { 
			System.out.println(0);
		} else if (ripen == 0){
			System.out.println(-1);
		} else {
			int time = 0;
			while (!que.isEmpty()) {
				int[] cur = que.poll();
				for (int k = 0; k < 4; k++) { // 상하좌우 탐색
					int x = cur[0] + dx[k];
					int y = cur[1] + dy[k];
					
					if (!isBoudary(x, y) || board[x][y] == 1 || board[x][y] == -1) {
						// 범위에서 벗어나거나 이미 토마토가 익었거나 빈칸인 경우 패스
						continue;
					}
					board[x][y] = 1; // 토마토 익히기
					ripen++;
					que.offer(new int[] {x, y, cur[2]+1});
					time = cur[2] + 1;
				}
			}
			if (ripen == (N*M - none)) {
				System.out.println(time);
			} else {
				System.out.println(-1);
			}
		}
	}
	private static boolean isBoudary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
