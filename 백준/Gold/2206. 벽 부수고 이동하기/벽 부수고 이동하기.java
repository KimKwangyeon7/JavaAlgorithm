

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {
	static int N;
	static int M;
	static int[][] board;
	static int answer;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		answer = Integer.MAX_VALUE;
		bfs();
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	private static void bfs() {
		Queue<int[]> pq = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		pq.offer(new int[] {0, 0, 1, 0}); 
		visited[0][0][0] = true;
		
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			if (tmp[0] == N-1 && tmp[1] == M-1) {
				answer = tmp[2];
				return;
			}

			for (int k = 0; k < 4; k++) {
				int x = tmp[0] + dx[k];
				int y = tmp[1] + dy[k];
				int wall = tmp[3];
				
				if (!isBoundary(x, y)) {
					continue;
				}
				if (board[x][y] == 1) { // 벽인 경우
	                if (wall == 0 && !visited[x][y][1]) {
	                    visited[x][y][1] = true;  // 벽을 부수고 이동
	                    pq.offer(new int[] {x, y, tmp[2] + 1, 1});
	                }
	            } else { // 빈 칸인 경우
	                if (!visited[x][y][wall]) {
	                    visited[x][y][wall] = true;
	                    pq.offer(new int[] {x, y, tmp[2] + 1, wall});
	                }
	            }

			}
		}
	}

//	private static void dfs(int x, int y, int cnt, boolean[][] visited, boolean rock) {
//		if (x == N-1 && y == M-1) {
//			answer = Math.min(answer, cnt);
//			return;
//		}
//		for (int k = 0; k < 4; k++) {
//			int xx = x + dx[k];
//			int yy = y + dy[k];
//			
//			if (!isBoundary(xx, yy) || visited[xx][yy]) {
//				continue;
//			}
//			if (board[xx][yy] == 1) {
//				if (rock) {
//					continue;
//				}
//				rock = true;
//				visited[xx][yy] = true;
//				dfs(xx, yy, cnt+1, visited, rock);
//				rock = false;
//				visited[xx][yy] = false;
//			} else {
//				visited[xx][yy] = true;
//				dfs(xx, yy, cnt+1, visited, rock);
//				visited[xx][yy] = false;
//			}
//		}
//	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
