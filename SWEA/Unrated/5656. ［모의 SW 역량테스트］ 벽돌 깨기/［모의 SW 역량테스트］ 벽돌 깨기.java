
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김광연 SW_5656_벽돌깨기_김광연 구현하려고 했는데 실패!
 */
public class Solution {
	static int N, ans, W, H;
	static int[] res;
	static int[][] board;
	static int[][] tmp;
	static boolean[][] visit;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 떨어뜨릴 구슬 개수
			W = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 세로

			board = new int[H][W];
			tmp = new int[H][W];
			for (int i = 0; i < H; i++) { // 벽돌 정보 입력 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = board[i][j];
				}
			}
			
			res = new int[N];
			ans = 2147000000;

			// 시작열 정하기
			DFS(0);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}

	private static void DFS(int cnt) {
		if (cnt == N) {

			destroyBricks(res);
			ans = Math.min(ans, getBricks());
			
			for (int i = 0; i < H; i++) {
				board[i] = tmp[i].clone();
			}
			return;
		}
		for (int i = 0; i < W; i++) {
			res[cnt] = i;
			DFS(cnt + 1);
		}
	}

	private static int getBricks() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void destroyBricks(int[] r) { //
		int sx = 0;
		int sy = 0;
		int row = 0;
		
		for (int x : r) { // 해당 열에서 구슬 떨어뜨림
			row = 0;
			while (row < H) {
				//visit = new boolean[H][W];
				if (board[row][x] != 0) { // 벽돌과 만나면
					sx = row;
					sy = x;
					break;
				}
				row++;
			}
			splashBricks(sx, sy);
			locateBricks();
			
		}
	}

	private static void splashBricks(int row, int col) {
		Queue<int[]> qu = new ArrayDeque<>();

		// 매개변수로 받은 처음 만난 블록의 행, 열 값과 블록의 숫자 집어넣음
		qu.add(new int[] { row, col, board[row][col] });
		// 해당 블록이 터졌음을 의미
		board[row][col] = 0;

		while (!qu.isEmpty()) {
			int[] cur = qu.poll();

			// 블록의 숫자는 터지는 범위
			int cnt = cur[2];

			// 터지는 범위는 크기-1이므로 num미만까지 반복
			for (int i = 1; i < cnt; i++) {
				// 4방탐색하면서 폭발하는 크기를 위해 방향델타*i
				for (int k = 0; k < 4; k++) {
					int x = cur[0] + dx[k] * i;
					int y = cur[1] + dy[k] * i;

					// 배열을 벗어나면 패스
					if (!isBoundary(x, y) || board[x][y] == 0)
						continue;

					// 0이 아닌 블록을 만나면
					if (board[x][y] != 0) {
						// 해당 블록의 행, 열, 숫자 큐에 입력
						qu.offer(new int[] { x, y, board[x][y] });
						// 해당 블록이 터졌으므로 0처리
						board[x][y] = 0;
					}
				}
			}
		}
	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

	private static void locateBricks() {
		// 공중에 떠 있는 벽돌 떨어뜨려주기
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < W; i++) { // 첫번째 열부터 벽돌 떨구기
			// 0이 아닌 값만 스택에 쌓기
			for (int j = 0; j < H; j++) {
				if (board[j][i] != 0) {
					stack.add(board[j][i]);
					//tmp[j][i] = 0;
				}
			}
			for (int j = H-1; j >= 0; j--) {
				if (stack.isEmpty()) {
					board[j][i] = 0;
				} else {
					board[j][i] = stack.pop();
				}
			}
//			// 스택에 쌓인 값을 다시 바닥부터 쌓기
//			int idx = H - 1;
//			while (!stack.isEmpty()) {
//				tmp[idx--][i] = stack.pop();
//			}
		}
	}

}

//int cnt = board[row][col] - 1; // 벽돌 깰 횟수
//visit[row][col] = true;
//// 위로 벽깨기
//int up = row; // 가장 상단 사이에 있는 벽돌 개수
//if (up > 0 && cnt > 0) {
//	if (up < cnt) {
//		for (int i = row - 1; i >= 0; i--) {
//			tmp[i][col] = 0;
//		}
//	} else if (up == cnt) {
//		for (int i = row - 1; i >= 0; i--) {
//			tmp[i][col] = 0;
//		}
//		if (!visit[0][col]) {
//			splashBricks(0, col);
//		}
//	} else {
//		for (int i = row - 1; i > row - 1 - cnt; i--) {
//			tmp[i][col] = 0;
//		}
//		if (!visit[row-cnt][col]) {
//			splashBricks(row - cnt, col);
//		}
//	}
//}
//// 아래로 벽깨기
//int down = H - row - 1; // 가장 하단 사이에 있는 벽돌 개수
//if (down > 0 && cnt > 0) {
//	if (down < cnt) {
//		for (int i = down + 1; i < H; i++) {
//			tmp[i][col] = 0;
//		}
//	} else if (down == cnt) {
//		for (int i = row + 1; i < H; i++) {
//			tmp[i][col] = 0;
//		}
//		if (!visit[H-1][col]) {
//			splashBricks(H - 1, col);
//		}
//	} else {
//		for (int i = row + 1; i < row + 1 + cnt; i++) {
//			tmp[i][col] = 0;
//		}
//		if (!visit[row+cnt][col]) {
//			splashBricks(row + cnt, col);
//		}
//	}
//}
//// 왼쪽으로 벽깨기
//int left = col; // 가장 좌측 사이에 있는 벽돌 개수
//if (left > 0 && cnt > 0) {
//	if (left < cnt) {
//		for (int i = col - 1; i >= 0; i--) {
//			tmp[row][i] = 0;
//		}
//	} else if (left == cnt) {
//		for (int i = col - 1; i >= 0; i--) {
//			tmp[row][i] = 0;
//		}
//		if (!visit[row][0]) {
//			splashBricks(row, 0);
//		}
//	} else {
//		for (int i = col - 1; i > col - 1 - cnt; i--) {
//			tmp[row][i] = 0;
//		}
//		if (!visit[row][col-cnt]) {
//			splashBricks(row, col - cnt);
//		}
//	}
//}
//// 오른쪽으로 벽깨기
//int right = W - col - 1; // 가장 하단 사이에 있는 벽돌 개수
//if (right > 0 && cnt > 0) {
//	if (right < cnt) {
//		for (int i = col + 1; i < W; i++) {
//			tmp[row][i] = 0;
//		}
//	} else if (right == cnt) {
//		for (int i = col + 1; i < W; i++) {
//			tmp[row][i] = 0;
//		}
//		if (!visit[row][W-1]) {
//			splashBricks(row, W - 1);
//		}
//	} else {
//		for (int i = col + 1; i < col + 1 + cnt; i++) {
//			tmp[i][col] = 0;
//		}
//		if (!visit[row][col+cnt]) {
//			splashBricks(row, col + cnt);
//		}
//	}
//}
