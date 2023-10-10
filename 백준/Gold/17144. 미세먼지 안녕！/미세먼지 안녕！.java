import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Main {
	
	static int R, C;
	// 우 상 좌 하 : 위쪽
	// 우 하 좌 상 : 아래쪽
	static int[] dx = {0, -1, 0, 1}; // 위쪽 기준
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[R][C];
		int[][] tmp = new int[R][C];
		int sx = 0;
		int sy = 0;
		int ex = 0;
		int ey = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = board[i][j];
					if (board[i][j] == -1) {
						ex = i;
						ey = j;
					}
				}
		}
		sx = ex-1;
		sy = ey;
		while (T > 0) {
			// 미세먼지 확산시키기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (board[i][j] != 0) {
						int div = board[i][j] / 5; // 상하좌우에 더해줄 값
						for (int k = 0; k < 4; k++) { // 상하좌우 탐색
							int x = i + dx[k];
							int y = j + dy[k];
							
							if (!isBoundary(x, y) || board[x][y] == -1) {
								continue;
							}
							tmp[x][y] += div;
							tmp[i][j] -= div;
						}
					}
				}
			}
			// 공기청정기 작동
			// 위쪽
			int x = sx;
			int y = sy;
			int dir = 0;
			int t = 0;
			while (true) {
				if (!isBoundary(x+dx[dir], y+dy[dir])) { // 더이상 못가면 다음 방향으로
					dir++;
				}
				if (tmp[x+dx[dir]][y+dy[dir]] == -1) {
					break;
				}
				int a = tmp[x+dx[dir]][y+dy[dir]];
				tmp[x+dx[dir]][y+dy[dir]] = t;
				x += dx[dir];
				y += dy[dir];
				t = a;
			}
			
			// 아래쪽
			x = ex;
			y = ey;
			dir = 0;
			t = 0;
			while (true) {
				if (!isBoundary(x+dx[dir], y+dy[dir])) { // 더이상 못가면 다음 방향으로
					dir--;
					if (dir < 0) {
						dir = 3;
					}
				}
				if (tmp[x+dx[dir]][y+dy[dir]] == -1) {
					break;
				}
				int a = tmp[x+dx[dir]][y+dy[dir]];
				tmp[x+dx[dir]][y+dy[dir]] = t;
				x += dx[dir];
				y += dy[dir];
				t = a;
			}			
			
			// 배열 다시 같게 하기
			for (int i = 0; i < R; i++) {
				board[i] = tmp[i].clone();
			}
			T--;
		}
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += board[i][j];
			}
		}
		System.out.println(sum+2);
//		for (int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
		
	}

	private static boolean isBoundary(int i, int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}

}