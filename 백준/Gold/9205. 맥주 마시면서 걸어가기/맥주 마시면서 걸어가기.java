
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 
 * BJ_9205_맥주마시면서걸어가기_김광연
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] v = new int[N+2][2]; // x, y 좌표 받을 배열
			int[][] board = new int[N+2][N+2]; // 두 좌표 간 거리 담을 배열
			
			for (int i = 0; i < N+2; i++) {
				st = new StringTokenizer(br.readLine());
				v[i][0] = Integer.parseInt(st.nextToken());
				v[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N+2; i++) { // 거리 구하기
				for (int j = 0; j < N+2; j++) {
					board[i][j] = dis(v[i], v[j]);
					if (board[i][j] > 1000) { // 거리가 1000보다 크면 큰 값으로 초기화
						board[i][j] = 214700000;
					}
				}
			}
			
			for (int k = 0; k < N+2; k++) {
				for (int i = 0; i < N+2; i++) {
					for (int j = 0; j < N+2; j++) {
						if (i == k || j == k || i == j) {
							continue;
						}
						board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
					}
				}
			}
			if (board[0][N+1] == 214700000) {
				sb.append("sad").append("\n");
			} else {
				sb.append("happy").append("\n");
			}
		}
		System.out.println(sb);
	}

	static int dis(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
	}

}
