import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		int[][] friends = new int[N*N+1][N*N+1];
		int a = 0;
		int b = 0;
		int empty = 0;
		int like = 0;
	
		// 각 학생마다 좋아하는 친구 4명씩 입력 받아서 배열에 저장하기
		for (int i = 1; i < N*N+1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()); // A -> B일 때 A 역할
			for (int j = 0; j < 4; j++) {
				b = Integer.parseInt(st.nextToken()); // A -> B일 때 B 역할
				friends[a][b] = 1;
			}
			
			int L = -1; // 좋아하는 학생 수의 최대값
			int E = -1; // 빈자리의 최대값
			int sx = 0;
			int sy = 0;		
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					empty = 0;
					like = 0;
					if (board[x][y] != 0) {
						continue;
					}
					for (int k = 0; k < 4; k++) {
						int xx = x + dx[k];
						int yy = y + dy[k];
						
						if (!isBoundary(xx, yy)) {
							continue;
						}
						if (board[xx][yy] == 0) {
							empty++;
						} else if (friends[a][board[xx][yy]] == 1) {
							like++;
						}
					}
					// 조건을 만족하는 자리 고르는 경우: 1. 좋아하는 학생 수가 더 많음 -> 해당 자리로 
					// 						 	2. 좋아하는 학생 수는 같고 빈자리가 더 많으면 -> 해당 자리로
					//							3. 다 같으면 행, 열이 작은 순으로 자리에 앉기 때문에 바꿔줄 필요 X
					if (like > L || (like == L && empty > E)) {
						sx = x;
						sy = y;
						L = like;
						E = empty;
					}
				}
			}
			board[sx][sy] = a;
		}
		
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt = 0;
				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					
					if (!isBoundary(x, y)) {
						continue;
					}
					if (friends[board[i][j]][board[x][y]] == 1) {
						cnt++;
					}
				}
				if (cnt == 1) {
					sum += 1;
				} else if (cnt == 2) {
					sum += 10;
				} else if (cnt == 3){
					sum += 100;
				} else if (cnt == 4) {
					sum += 1000;
				}
			}
		}
		System.out.println(sum);

	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
