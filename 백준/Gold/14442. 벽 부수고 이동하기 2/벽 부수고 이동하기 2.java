



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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
        Queue<int[]> qu = new ArrayDeque<>();
        for (int i = 0; i < K+1; i++) {
        	visited[0][0][i] = true;
        }
        
        qu.offer(new int[] {0, 0, 1, 0, -1, -1});
        int ans = -1;
        while (!qu.isEmpty()) {
        	int[] cur = qu.poll();
        	int x = cur[0];
        	int y = cur[1];
        	int dis = cur[2];
        	int wall = cur[3];
        	int prevX = cur[4];
        	int prevY = cur[5];
        	
        	if (x == N-1 && y == M-1) {
        		ans = dis;
        		break;
        	}
        	for (int k = 0; k < 4; k++) {
        		int nx = x + dx[k];
        		int ny = y + dy[k];
        		
        		if (!isBoundary(nx, ny, N, M) || (prevX == nx && prevY == ny)) {
        			continue;
        		}
        		if (board[nx][ny] == 1) {
        			if (wall >= K || visited[nx][ny][wall+1]) {
        				continue;
        			} else {
        				visited[nx][ny][wall+1] = true;
        				qu.offer(new int[] {nx, ny, dis+1, wall+1, x, y});
        			}
        		} else {
        			if (!visited[nx][ny][wall]) {
        				visited[nx][ny][wall] = true;
        				qu.offer(new int[] {nx, ny, dis+1, wall, x, y});
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
