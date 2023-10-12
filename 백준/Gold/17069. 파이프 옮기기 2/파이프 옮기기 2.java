
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
		int[][] board = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][][] map = new long[N+1][N+1][3]; // 들어오는 파이프의 방향까지 추가: 0-가로 1-세로 2-대각선
		map[1][2][0] = 1;

		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				if (board[i][j] != 1) {
						map[i][j][0] += map[i][j-1][0] + map[i][j-1][2]; // 가로	
						
						map[i][j][1] += map[i-1][j][1] + map[i-1][j][2]; // 세로
						
						if (board[i][j-1] != 1 && board[i-1][j] != 1) {
							map[i][j][2] += map[i-1][j-1][0] + map[i-1][j-1][1] + map[i-1][j-1][2]; // 대각선
						}			
				}
			}
		}
		System.out.println(map[N][N][0]+map[N][N][1]+map[N][N][2]);
//		for (int i = 0; i < N+1; i++) {
//			for (int j = 0; j < N+1; j++) {
//				System.out.print(map[i][j][0]+ map[i][j][1] + map[i][j][2] + " ");
//			}
//			System.out.println();
//		}
	}

}