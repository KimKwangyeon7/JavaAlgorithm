import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_1987_알파벳
 * 메모리 23,596 kb 시간 4,576 ms
 * 아이디어
 * DFS 활용 -> 모든 경우 다 가보면서 최대값 구하기
 * 모든 경우를 다 가봐서 그런지 실행시간이 너무 오래 걸림
 * BFS로 조건 걸면서 하면 더 빠르게 될 거 같은데 고민 중
 * @author 김광연
 *
 */
public class Main {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int R, C, max, check;
	//static List<Character> list; 
	static char[][] board;
	//static boolean[][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행의 수
		C = Integer.parseInt(st.nextToken()); // 열의 수
		board = new char[R+1][C+1]; // (1, 1)부터 시작하므로 사이즈 1씩 늘림
		//visit = new boolean[R+1][C+1];
		
		for (int i = 1; i <= R; i++) { // 보드에 적힌 알파벳들 받기
			String tmp = br.readLine();
			for (int j = 1; j <= C; j++) {
				board[i][j] = tmp.charAt(j-1);
			}
		}
		//list = new ArrayList<>(); // 알파벳 체크할 배열
		//list.add(board[1][1]); // 시작 위치 알파벳 저장
		max = 0; // 최대값 구할 변수 (0으로 초기화)
		check = 1 << ((int)board[1][1] - 65);
		dfs(1, 1, 1); // (1, 1)에서 시작
		System.out.println(max);
	
	}
	
	static void dfs(int x, int y, int cnt) {
		if (max < cnt) { // 해당 위치에 왔을 때 값이 최대값보다 크면 최대값 교체
			max = cnt;
		}
		for (int k = 0; k < 4; k++) {
			int xx = x + dx[k];
			int yy = y + dy[k];
			
			if (!isBoundary(xx, yy) || ((check >> ((int)board[xx][yy] - 65)) & 1) == 1){
				continue;
			}
			check += 1 << ((int)board[xx][yy] - 65);
			dfs(xx, yy, cnt+1);
			check -= 1 << ((int)board[xx][yy] - 65);
		}
	}
	private static boolean isBoundary(int x, int y) {
		return x >= 1 && x <= R && y >= 1 && y <= C;
	}
	/*private static int getIndex(int x, int y) {
		int idx = 0;
		for (char l: list) {
			if (l == board[x][y]) {
				return idx;
			}
			idx ++;
		}
		return -1;
	}*/

}
