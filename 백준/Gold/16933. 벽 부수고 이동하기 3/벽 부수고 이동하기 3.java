
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Dot {
		int x;
		int y;
		int dis;
		int wall;
		int prevX;
		int prevY;
		
		public Dot(int x, int y, int dis, int wall, int prevX, int prevY) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.wall = wall;
			this.prevX = prevX;
			this.prevY = prevY;
		}
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
	
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
        	String tmp = br.readLine();
        	for (int j = 0; j < M; j++) {
        		board[i][j] = tmp.charAt(j) - '0';
        	}
        }
        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Dot> qu = new ArrayDeque<>();
        for (int i = 0; i < K+1; i++) {
        	visited[0][0][i] = true;
        }
        
        qu.offer(new Dot(0, 0, 1, 0, -1, -1));
        int ans = -1;
        while (!qu.isEmpty()) {
        	Dot cur = qu.poll();
        	
        	if (cur.x == N-1 && cur.y == M-1) {
        		ans = cur.dis;
        		break;
        	}
        	for (int k = 0; k < 4; k++) {
        		int nx = cur.x + dx[k];
        		int ny = cur.y + dy[k];
        		
        		if (!isBoundary(nx, ny, N, M) || (cur.prevX == nx && cur.prevY == ny)) {
        			continue;
        		}
        		if (board[nx][ny] == 1) {
        			if (cur.wall >= K || visited[nx][ny][cur.wall+1]) {
        				continue;
        			}
        			if (cur.dis % 2 == 0) {
//        				if (!visited[nx][ny][cur.wall]) {
//        					visited[nx][ny][cur.wall] = true;
//        					qu.offer(new Dot(nx, ny, cur.dis+1, cur.wall+1, cur.x, cur.y));
//        				}
        				qu.offer(new Dot(cur.x, cur.y, cur.dis+1, cur.wall, cur.x, cur.y));
        			} else {
        				if (!visited[nx][ny][cur.wall+1]) {
        					visited[nx][ny][cur.wall+1] = true;
        					qu.offer(new Dot(nx, ny, cur.dis+1, cur.wall+1, cur.x, cur.y));
        				}
        			}
        		} else {
        			if (!visited[nx][ny][cur.wall]) {
        				visited[nx][ny][cur.wall] = true;
        				qu.offer(new Dot(nx, ny, cur.dis+1, cur.wall, cur.x, cur.y));
        			}
        		}
        	}
        }
        System.out.println(ans);
	}
	private static boolean isBoundary(int nx, int ny, int N, int M) {
		return nx >= 0 && nx < N && ny >= 0 && ny < M;
	}
}
