

import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */

public class Main {
	static int[][] board;
	static int N;
	static int answer;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min,  board[i][j]);
				max = Math.max(max,  board[i][j]);
			}
		}
		answer = 1;
		boolean[][] visited;
		for (int i = min; i < max; i++) {
			visited = new boolean[N][N];
			int res = bfs(i, visited);
			if (res == 0) {
				break;
			}
			answer = Math.max(answer, res);
		}
		System.out.println(answer);
	}
	
	private static int bfs(int h, boolean[][] visited) {
		Queue<int[]> qu = new ArrayDeque<>();
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] > h && !visited[i][j]) {
					cnt++;
					visited[i][j] = true;
					qu.offer(new int[] {i, j});
					
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						
						for (int k = 0; k < 4; k++) {
							int x = tmp[0] + dx[k];
							int y = tmp[1] + dy[k];
							
							if (!isBoundary(x, y) || board[x][y] <= h || visited[x][y]) {
								continue;
							}
							visited[x][y] = true;
							qu.offer(new int[] {x, y});
						}
					}
				}
			}
		}
		return cnt;
	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
