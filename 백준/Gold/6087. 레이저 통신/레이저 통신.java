
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int[] dx = new int[] {1, 0, -1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // 열
		int H = Integer.parseInt(st.nextToken()); // 행
		
		char[][] board = new char[H][W];
		int[] gates = new int[4];
		int[][][] dis = new int[H][W][4];
		Arrays.fill(gates, -1);
		for (int i = 0; i < H; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < W; j++) {
				Arrays.fill(dis[i][j], Integer.MAX_VALUE);
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'C') {
					if(gates[0] == -1) {
						gates[0] = i;
						gates[1] = j;
					} else {
						gates[2] = i;
						gates[3] = j;
					}
				}
			}
		}
		
		
		Queue<int[]> qu = new ArrayDeque<>();
		qu.offer(new int[] {gates[0], gates[1] , -1, 0});
		for (int i = 0; i < 4; i++) {
			dis[gates[0]][gates[1]][i] = 0;
		}
		
		int answer = Integer.MAX_VALUE;
		while (!qu.isEmpty()) {
			int[] t = qu.poll();
			int[] cur = new int[2];
			cur[0] = t[0];
			cur[1] = t[1];
			int prevDir = t[2];
			
			if (cur[0] == gates[2] && cur[1] == gates[3]) {
				answer = Math.min(answer, t[3]);
				continue;
			}
			
			for (int k = 0; k < 4; k++) {
				int nx = cur[0] + dx[k];
				int ny = cur[1] + dy[k];
				
				if (!isBoundary(nx, ny, H, W) || board[nx][ny] == '*') {
					continue;
				}
				int cnt = t[3];
				if (prevDir != -1 && k != prevDir) {
					cnt++;
				}
				if (dis[nx][ny][k] > cnt) {
					dis[nx][ny][k] = cnt;
					qu.offer(new int[] {nx, ny, k, cnt});
				}
			}
		}
		System.out.println(answer);
	}
	private static boolean isBoundary(int nx, int ny, int H, int W) {
		return nx >= 0 && nx < H && ny >= 0 && ny < W;
	}

}
