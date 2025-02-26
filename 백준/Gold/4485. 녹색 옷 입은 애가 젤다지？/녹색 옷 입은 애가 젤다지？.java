
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static class Dot implements Comparable<Dot>{
		int x;
		int y;
		int dis;
		
		public Dot(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		@Override
		public int compareTo(Dot o) {
			return Integer.compare(this.dis, o.dis);
		}
		
	}
	
	
		
	static int N, ans;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] board;
	static int[][] minMap;
	//static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			sb.append("Problem ").append(tc).append(": ");
			board = new int[N][N];
			minMap = new int[N][N];
			
			ans = 2147000000;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					minMap[i][j] = 2147000000;
				}
			}
			tc++;
			
			minMap[0][0] = board[0][0];
			bfs(0, 0);
			sb.append(minMap[N-1][N-1]).append("\n");
			
		}
		System.out.println(sb);
	}
	static void bfs(int x, int y) {
		PriorityQueue<Dot> qu = new PriorityQueue<>();
		qu.offer(new Dot(x, y, board[x][y]));
		while (!qu.isEmpty()) {
			Dot tmp = qu.poll();
			for (int k = 0; k < 4; k++) {
				int xx = tmp.x+dx[k];
				int yy = tmp.y+dy[k];
				
				if (!isBoundary(xx, yy) || minMap[xx][yy] <= tmp.dis+board[xx][yy]) {
					continue;
				}
				minMap[xx][yy] = tmp.dis + board[xx][yy];
				qu.offer(new Dot (xx, yy, tmp.dis+board[xx][yy]));
			}
		}
		
	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
