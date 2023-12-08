
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static int[] dx = {-1, 0, 1}; // 오른쪽 대각위, 오른쪽, 오른쪽 대각아래 순서로
	static int R, C;
	static char[][] board;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행 수
		C = Integer.parseInt(st.nextToken()); // 열 수
		
		board = new char[R][C]; 
		for (int i = 0; i < R; i++) { // 입력 받기
			String tmp = br.readLine();
			for (int j = 0; j  < C; j++) {
				board[i][j] = tmp.charAt(j);
			}
		}
		
		cnt = 0;
		for (int i = 0; i < R; i++) { // 첫번째 열을 시작점으로
			board[i][0] = 'x'; // 방문 표시
			DFS(i, 0);
		}
		System.out.println(cnt);
	}

	private static boolean DFS(int i, int j) {
		if (j == C-1) { // 마지막 열에 도착하면 카운트 후 true 반환
			cnt++;
			return true;
		}
		for (int k = 0; k < 3; k++) {
			int x = i + dx[k];
			int y = j + 1;
			
			if (!isBoundary(x, y) || board[x][y] == 'x') {
				continue;
			}
			board[x][y] = 'x';
			if (DFS(x, y)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isBoundary(int x, int y) { // 범위 안에 드는지 확인하는 메서드
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
