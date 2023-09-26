import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] board, tmp;
	static int N, M, len;
	static int[] res;
	static List<int[]> list;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 가로
		M = Integer.parseInt(st.nextToken()); // 세로
		board = new int[N][M];
		list = new ArrayList<>(); // 빈칸 좌표 저장할 list
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		tmp = new int[N][M];
		len = list.size();
		ans = 0;
		res = new int[3];
		combi(0, 0);
		System.out.println(ans);
	}

	static void resetMap() {
		for (int i = 0; i < N; i++) {
			tmp[i] = board[i].clone();
		}
	}
	static void combi(int size, int start) {
		if (size == 3) {
			resetMap();
			for (int x: res) {
				tmp[list.get(x)[0]][list.get(x)[1]] = 1;
			}
			ans = Math.max(ans, getSafeZone());
			return;
		}
		for (int i = start; i < len; i++) {
			res[size] = i;
			combi(size+1, i+1);
		}
	}
	
	static int getSafeZone() {
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 2 && !visit[i][j]) {
					bfs(i, j, visit);
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void bfs(int x, int y, boolean[][] visit) {
		Queue<int[]> qu = new ArrayDeque<>();
		qu.offer(new int[] {x, y});
		visit[x][y] = true;
		while (!qu.isEmpty()) {
			int[] cur = qu.poll();
			for (int k = 0; k < 4; k++) {
				int xx = cur[0] + dx[k];
				int yy = cur[1] + dy[k];
			
				if (!isBoundary(xx, yy) || visit[xx][yy] || tmp[xx][yy] != 0 ) {
					continue;
				}
				tmp[xx][yy] = 2; // 바이러스로 변함
				visit[xx][yy] = true;
				qu.offer(new int[] {xx, yy});
			}
		}
		
	}

	private static boolean isBoundary(int xx, int yy) {
		return xx >= 0 && xx < N && yy >= 0 && yy < M;
	}
}