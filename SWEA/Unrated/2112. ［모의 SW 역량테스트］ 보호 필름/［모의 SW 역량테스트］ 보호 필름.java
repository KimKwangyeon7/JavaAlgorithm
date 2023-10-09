import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Solution {

	static int D, W, ans;
	static int[][] board;
	static int K;
	static int[] res;
	static int[][] tmp;
	static int[] sub;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" "); // 출력 양식 저장
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 세로
			W = Integer.parseInt(st.nextToken()); // 가로
			K = Integer.parseInt(st.nextToken());

			board = new int[D][W];
			tmp = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					tmp[i][j] = board[i][j];
				}
			}

			// 열 검사하기
			int flag = 0;
			for (int j = 0; j < W; j++) {
				if (!checkCol(j)) {
					flag = 1;
					break;
				}
			}
			if (flag == 0 || K == 1) { // 약물 투입 없이 바로 테스트 통과
				sb.append(0).append("\n");
				continue;
			}

			ans = 0;
			for (int i = 1; i <= K; i++) {
				res = new int[i];
				combi(0, 0, i);
				if (ans != 0) {
					break;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void combi(int cnt, int start, int size) {
		if (cnt == size) {
			int len = res.length;
			sub = new int[len];
			dfs(0, len);

			// 배열 다시 원상복구
			for (int i = 0; i < D; i++) {
				board[i] = tmp[i].clone();
			}
			return;
		}
		for (int i = start; i < D; i++) {
			res[cnt] = i;
			combi(cnt + 1, i + 1, size);
		}
	}

	private static void dfs(int cnt, int size) {
		if (ans != 0) {
			return;
		}
		if (cnt == size) {
			int flag = 0;
			for (int i = 0; i < W; i++) {
				changeAtt(i);
				if (!checkCol(i)) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				ans = size;
			}
//			// 배열 다시 원상복구
//			for (int i = 0; i < D; i++) {
//				board[i] = tmp[i].clone();
//			}
			return;
		}
		sub[cnt] = 0;
		dfs(cnt + 1, size);
		sub[cnt] = 1;
		dfs(cnt + 1, size);
	}

	private static boolean checkCol(int col) {
		int cnt = 1;
		for (int i = 0; i < D - 1; i++) {
			if (board[i][col] == board[i + 1][col]) {
				cnt++;
				if (cnt >= K) {
					return true;
				}
			} else {
				cnt = 1;
			}
		}
		return false;
	}

	private static void changeAtt(int col) {
		int len = sub.length;
		for (int i = 0; i < len; i++) {
			board[res[i]][col] = sub[i];
		}
	}
}
