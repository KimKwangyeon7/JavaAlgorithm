

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int n;
	static int m;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); //가로
        int[][] board = new int[n][m];
        // 목표 지점
        int ex = -1; 
        int ey = -1;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                    ex = i;
                    ey = j;
                }
            }
        }
        boolean[][] visited = new boolean[n][m]; // 방문 체크할 배열
        
        Queue<int[]> qu = new ArrayDeque<>();
        qu.add(new int[] {ex, ey});
        visited[ex][ey] = true;
        // BFS 탐색
        while (!qu.isEmpty()) {
        	int[] tmp = qu.poll();
            for (int k = 0; k < 4; k++) {
                int x = tmp[0] + dx[k];
                int y = tmp[1] + dy[k];
                if (!isBoundary(x, y)) { // 범위에서 벗어나면 패스
                    continue;
                }
                if (visited[x][y] || board[x][y] == 0) { // 이미 방문한 곳이거나 갈 수 없는 곳이면 패스
                    continue;
                }
                qu.add(new int[] {x, y});
                visited[x][y] = true;
                board[x][y] = board[tmp[0]][tmp[1]] + 1; // 거리+1 하기
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
            	if (board[i][j] == 1 && !visited[i][j]) { // 갈 수 있는 곳이고 방문한 적이 없으면
            		board[i][j] = -1;
            	}
                sb.append(board[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        

	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
