import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N+1][N+1]; // 인덱스가 1부터 시작하기 때문에 1을 늘림
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] map = new int[N+1][N+1][3]; // 0->가로, 1->세로, 2->대각
		map[1][2][0] = 1;
		
		for (int m = 1; m < N+1; m++) {
			for (int n = 1; n < N+1; n++) { // 시작이 가로로 놓여있고 다음 행으로 내려가기 위해선 대각선으로 가는 방향말고 없으므로 3열부터 탐색함
				if (map[m][n][0] == 0 && map[m][n][1] == 0 && map[m][n][2] == 0) {
					if (board[m][n] == 0 && board[m-1][n] == 0 && m-1 >= 1) {
						map[m][n][1] = map[m-1][n][1] + map[m-1][n][2];
					}
					if (board[m][n] == 0 && board[m][n-1] == 0 && n-1 >= 1) {
						map[m][n][0] = map[m][n-1][0] + map[m][n-1][2];
					}
					if (m-1 >= 1 && n-1 >= 1 && board[m-1][n-1] == 0 && board[m][n] == 0 && board[m-1][n] == 0 && board[m][n-1] == 0) {
						map[m][n][2] = map[m-1][n-1][0] + map[m-1][n-1][1] + map[m-1][n-1][2];
					}
				}
			}
		}
		System.out.println(map[N][N][0]+map[N][N][1]+map[N][N][2]);
	}
}

