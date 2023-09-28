
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		final int INF = 214700000; 
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] board = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 0) { // 근접 노드가 아닌 경우
						board[i][j] = INF;
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j <N; j++) {
						if (i == j || i == k || j == k) {
							continue;
						}
						if (board[i][j] == INF && (board[i][k] == INF || board[k][j] == INF)) {
							continue;
						}
						board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
					}
				}
			}
//			
//			for (int i = 0; i < N ; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(board[i][j] + " ");
//				}
//				System.out.println();
//			}
			int part = 0;
			int total = INF;
			for (int i = 0; i < N ; i++) {
				part = 0;
				for (int j = 0; j < N; j++) {
					if (board[i][j] != INF) {
						part += board[i][j];
					}
				}
				total = Math.min(total, part);
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb);
	}
}
