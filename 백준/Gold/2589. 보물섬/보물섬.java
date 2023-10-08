import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	static int s, g;
	static char[][] board;
	static boolean[][] visit;
	static Queue<int[]> qu;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] check;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken()); // 세로 길이
		g = Integer.parseInt(st.nextToken()); // 가로 길이
		
		board = new char[s][g];
		for (int i = 0; i < s; i ++) {
			String tmp = br.readLine();
			board[i] = tmp.toCharArray();		
		}
//		for (int i = 0; i < s; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
		visit = new boolean[s][g];
		qu = new ArrayDeque<>();
		ans = 0;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < g; j++) {
				if (board[i][j] == 'L' && !visit[i][j]) {
					qu.offer(new int[] {i, j, 0}); // 좌표와 시작점과의 거리를 큐에 넣기
					visit[i][j] = true;
					makeLand();
				}
			}
		}
		System.out.println(ans);
		
	}
	private static void makeLand() {
		List<int[]> list = new ArrayList<>(); // 가장 멀리 있을 후보군들 저장할 리스트
		List<int[]> list2 = new ArrayList<>();
		int cnt = 0;
		int flag = 0;
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			ans = Math.max(ans, tmp[2]);
			cnt = 0;
			flag = 0;
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int xx = tmp[0] + dx[k];
				int yy = tmp[1] + dy[k];
				
				if (!isBoundary(xx, yy) || board[xx][yy] == 'W') {
					cnt++;
					continue;
				}
				if (visit[xx][yy]) {
					flag = 1;
					continue;
				}
				qu.offer(new int[] {xx, yy, tmp[2]+1}); 
				visit[xx][yy] = true;				
			}
			if (cnt == 3 && flag == 1) { // 상하좌우 중 오직 한 곳만 갈수 있는 경우 => 끝점인 경우
				list.add(new int[] {tmp[0], tmp[1]});
			} else {
				list2.add(new int[] {tmp[0], tmp[1]});
			}
		}
		
		if (list.isEmpty()) {
			for (int[] t: list2) {
				qu.clear();
				check = new boolean[s][g];
				qu.add(new int[] {t[0], t[1], 0});
				check[t[0]][t[1]] = true;
				ans = Math.max(ans, getDistance());
			}
		} else {
			for (int[] t: list) {
				qu.clear();
				check = new boolean[s][g];
				qu.add(new int[] {t[0], t[1], 0});
				check[t[0]][t[1]] = true;
				ans = Math.max(ans, getDistance());
			}
		}	
	}
	private static int getDistance() {
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int xx = tmp[0] + dx[k];
				int yy = tmp[1] + dy[k];
				
				if (!isBoundary(xx, yy) || check[xx][yy] || board[xx][yy] == 'W') {
					continue;
				}
				qu.offer(new int[] {xx, yy, tmp[2]+1}); 
				check[xx][yy] = true;			
			}
			if (qu.isEmpty()) {
				return tmp[2];
			}
		}
		return 0;
	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < s && y >= 0 && y < g;
	}


}
