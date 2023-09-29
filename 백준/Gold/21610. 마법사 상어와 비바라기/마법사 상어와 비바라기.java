
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 
 *         BJ_21610_마법사상어와비바라기_김광연 메모리 시간 아이디어
 *
 */
public class Main {

	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 }; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 순서
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayDeque<int[]> cloud;
	static int N;
	static int[][] board;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자의 길이
		int M = Integer.parseInt(st.nextToken()); // 구름의 이동 횟수

		board = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] move = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = Integer.parseInt(st.nextToken()) - 1;
			move[i][1] = Integer.parseInt(st.nextToken());
		}

		cloud = new ArrayDeque<>(); // 구름이 있는 곳의 좌표를 넣어둘 어레이데크
		// 구름의 처음 위치 넣기
		cloud.offer(new int[] { N - 2, 0 });
		cloud.offer(new int[] { N - 2, 1 });
		cloud.offer(new int[] { N - 1, 0 });
		cloud.offer(new int[] { N - 1, 1 });

		for (int[] tmp : move) {
			// 방문 배열 초기화
			visit = new boolean[N][N];
			// 구름 이동 + 해당 위치 물 1 증가 + 해당 위치 방문 체크
			moveCloud(tmp[0], tmp[1]);
			// 대각선에 있는 물이 있는 칸만큼 해당 좌표 물 양 증가
			crossCloud();
			// 구름 없애기 + 방문 x + 물 양 2이상인 곳 새로운 구름 생성
			cloud.clear();
			makeCloud();
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += board[i][j];
			}
		}
		System.out.println(ans);
	}

	static void moveCloud(int d, int s) {
		int cnt = 0;
		for (int[] c : cloud) {
			int x = c[0];
			int y = c[1];
			cnt = s;
			while (cnt > 0) {
				x += dx[d];
				y += dy[d];
				cnt--;
			}
			// 범위에 맞게 인덱스 설정하기
			if (x >= 0) {
				x = x % N;
			} else {
				x *= (-1);
				x = (N-1)  - ((x-1) % N);
			}
			if (y >= 0) {
				y = y % N;
			} else {
				y *= (-1);
				y = (N-1)  - ((y-1) % N);
			}
			// 바뀐 위치로
			c[0] = x;
			c[1] = y;
			// 해당 위치에 물+1
			board[x][y]++;
			// 해당 위치 방문 체크
			visit[x][y] = true;
		}
	}

	static void crossCloud() {
		for (int[] tmp : cloud) {
			for (int k = 1; k < 8; k += 2) {
				int x = tmp[0] + dx[k];
				int y = tmp[1] + dy[k];

				if (!isBoundary(x, y) || board[x][y] == 0) { // 범위에서 벗어나거나 물이 없으면 패스
					continue;
				}
				board[tmp[0]][tmp[1]]++;
			}

		}
	}

	static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j] && board[i][j] >= 2) { // 구름이 있던 곳이 아니고 물의 양이 2이상
					cloud.offer(new int[] { i, j }); // 해당 좌표 어레이데크에 넣기
					board[i][j] -= 2; // 해당 좌표의 물 양 2 감소
				}
			}
		}
	}

	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
