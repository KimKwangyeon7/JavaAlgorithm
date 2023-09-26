import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 김광연
 * 
 * BJ_2239_스도쿠_김광연
 * 메모리
 * 아이디어
 * 
 *
 */
public class Main {

	static int[][] board;
	static List<int[]> list;
	static int len;
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		board = new int[9][9];
		String tmp;
		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			tmp = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = tmp.charAt(j) - '0';
				
				if (board[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		len = list.size();
		dfs(0);
		
	}
	static boolean checkGaro(int x, int L) { // 가로 체크
		for (int i = 0; i < 9; i++) {
			if (board[list.get(L)[0]][i] == x) {
				return false;
			}
		}
		return true;
	}
	static boolean checkSero(int x, int L) { // 세로 체크
		for (int i = 0; i < 9; i++) {
			if (board[i][list.get(L)[1]] == x) {
				return false;
			}
		}
		return true;
	}
	static boolean checkSquare(int x, int L) { // 작은 사각형 체크
		int xx = list.get(L)[0] - (list.get(L)[0] % 3); // 작은 사각형 시작점
		int yy = list.get(L)[1] - (list.get(L)[1] % 3); 
		
		for (int i = xx; i < xx+3; i++) {
			for (int j = yy; j < yy+3; j++) {
				if (board[i][j] == x) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void dfs(int L) {
		if (L == len) { // 모든 빈칸을 다 채울 때
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);			
		}
		int x = list.get(L)[0];
		int y = list.get(L)[1];
		for (int i = 1; i <= 9; i++) {
			if (checkGaro(i, L) && checkSero(i, L) && checkSquare(i, L)) {
				board[x][y] = i;
				dfs(L+1);
				board[x][y] = 0;
			}
		}
		
	}
}
