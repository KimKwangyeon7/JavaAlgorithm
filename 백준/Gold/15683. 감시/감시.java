import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] board;
	static int n, m, size, ans;
	static List<int[]>[] list;
	static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dy = { 0, 1, 0, -1 };
	static int[] d;
	static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visit = new boolean[n][m];
		list = new ArrayList[6];
		for (int i = 1; i <= 5; i++) {
			list[i] = new ArrayList<int[]>();
		}
		int wall = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0 && board[i][j] != 6) {
					list[board[i][j]].add(new int[] { i, j});
				}
				if (board[i][j] == 6) {
					wall++;
				}
			}
		}
		size = 0;
		size += list[1].size();
		size += list[2].size();
		size += list[3].size();
		size += list[4].size();
	
		d = new int[size];
		ans = 0;
		permu(0);
		System.out.println(n*m-size-list[5].size()-ans-wall);
	}
	static void permu(int a) {
		if (a == size) {
			visit = new boolean[n][m];
			ans = Math.max(ans, getArea());
			return;
		}
		d[a] = 0;
		permu(a+1);
		d[a] = 1;
		permu(a+1);
		d[a] = 2;
		permu(a+1);
		d[a] = 3;
		permu(a+1);
	}
	static int getArea() {
		int sum = 0;
		int cnt = 0;
		for (int i = 1; i < 6; i++) {
			if (i == 1) { // 1 cctv
				for (int[] x : list[i]) {
					if (d[cnt] == 0) { // 위
						sum += canSee(x[0], x[1], 1, 0);
					} else if (d[cnt] == 1) { // 오른쪽
						sum += canSee(x[0], x[1], 1, 1);
					} else if (d[cnt] == 2) { // 아래
						sum += canSee(x[0], x[1], 1, 2);
					} else { // 왼쪽
						sum += canSee(x[0], x[1], 1, 3);
					}
					cnt++;
				}
			} else if (i == 2) { // 2 cctv
				for (int[] x : list[i]) {
					if (d[cnt] == 0 || d[cnt] == 2) { // 위 아래
						sum += canSee(x[0], x[1], 2, 0);
					} else { // 좌 우
						sum += canSee(x[0], x[1], 2, 1);
					}
					cnt++;
				}
			} else if (i == 3) { // 3 cctv
				for (int[] x : list[i]) {
					if (d[cnt] == 0) { // 위 오른쪽
						sum += canSee(x[0], x[1], 3, 0);
					} else if (d[cnt] == 1) { // 오른쪽 아래
						sum += canSee(x[0], x[1], 3, 1);
					} else if (d[cnt] == 2) { // 아래 왼쪽
						sum += canSee(x[0], x[1], 3, 2);
					} else { // 왼족 위
						sum += canSee(x[0], x[1], 3, 3);
					}
					cnt++;
				}
			} else if (i == 4) { // 4 cctv
				for (int[] x : list[i]) {
					if (d[cnt] == 0) { // 위 왼쪽 오른쪽
						sum += canSee(x[0], x[1], 4, 0);
					} else if (d[cnt] == 1) { // 오른쪽 위 아래
						sum += canSee(x[0], x[1], 4, 1);
					} else if (d[cnt] == 2) { // 아래 오른쪽 왼쪽
						sum += canSee(x[0], x[1], 4, 2);
					} else { // 왼쪽 아래 위
						sum += canSee(x[0], x[1], 4, 3);
					}
					cnt++;
				}
			} else { // 5 cctv
				for (int[] x : list[i]) { // 상하좌우
					sum += canSee(x[0], x[1], 5, 0);
				}
			}
		}
		return sum;
	}

	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	static int canSee(int x, int y, int cctv, int dir) {
		int cnt = 0; // 볼 수 있는 구역 수
		int tx = x;
		int ty = y;
		if (cctv == 1) {
			x = tx;
			y = ty;
			while (isBoundary(x + dx[dir], y + dy[dir])) {
				if (board[x + dx[dir]][y + dy[dir]] == 6) { // 벽만나면
					break;
				}
				x += dx[dir];
				y += dy[dir];
				if (board[x][y] != 0 || visit[x][y]) { // cctv가 있으면 cnt는 증가안하고 패스
					continue;
				}
				cnt++; // 카운트 증가
				visit[x][y] = true;
			}
			return cnt;
		} else if (cctv == 2) {
			// dir 방향
			for (int i = 0; i < 3; i += 2) {
				x = tx;
				y = ty;
				while (isBoundary(x + dx[dir + i], y + dy[dir + i])) {
					if (board[x + dx[dir + i]][y + dy[dir + i]] == 6) { // 벽만나면
						break;
					}
					x += dx[dir + i];
					y += dy[dir + i];
					if (board[x][y] != 0 || visit[x][y]) { // cctv가 있으면 cnt는 증가안하고 패스
						continue;
					}
					cnt++; // 카운트 증가
					visit[x][y] = true;
				}
			}
			return cnt;
		} else if (cctv == 3) {
			// dir 방향
			for (int i = 0; i < 2; i++) {
				x = tx;
				y = ty;
				while (isBoundary(x + dx[(dir + i) % 4], y + dy[(dir + i) % 4])) {
					if (board[x + dx[(dir + i) % 4]][y + dy[(dir + i) % 4]] == 6) { // 벽만나면
						break;
					}
					x += dx[(dir + i) % 4];
					y += dy[(dir + i) % 4];
					if (board[x][y] != 0 || visit[x][y]) { // cctv가 있으면 cnt는 증가안하고 패스
						continue;
					}
					cnt++; // 카운트 증가
					visit[x][y] = true;
				}
			}
			return cnt;
		} else if (cctv == 4) {
			// dir 방향부터 3방향 확인
			for (int i = 0; i < 3; i++) {
				x = tx;
				y = ty;
				while (isBoundary(x + dx[(dir + i) % 4], y + dy[(dir + i) % 4])) {
					if (board[x + dx[(dir + i) % 4]][y + dy[(dir + i) % 4]] == 6) { // 벽만나면
						break;
					}
					x += dx[(dir + i) % 4];
					y += dy[(dir + i) % 4];
					if (board[x][y] != 0 || visit[x][y]) { // cctv가 있으면 cnt는 증가안하고 패스
						continue;
					}
					cnt++; // 카운트 증가
					visit[x][y] = true;
				}
			}
			return cnt;
		} else {
			for (int i = 0; i < 4; i++) {
				x = tx;
				y = ty;
				while (isBoundary(x + dx[i], y + dy[i])) {
					if (board[x + dx[i]][y + dy[i]] == 6) { // 벽만나면
						break;
					}
					x += dx[i];
					y += dy[i];
					if (board[x][y] != 0 || visit[x][y]) { // cctv가 있으면 cnt는 증가안하고 패스
						continue;
					}
					cnt++; // 카운트 증가
					visit[x][y] = true;
				}
			}
			return cnt;
		}
	}
}
