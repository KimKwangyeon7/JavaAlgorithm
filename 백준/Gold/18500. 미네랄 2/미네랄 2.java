

import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static char[][] board;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        board = new char[R+1][C+1];
        for (int i = 1; i <= R; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < C; j++) {
        		board[i][j+1] = str.charAt(j);
        	}
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
        	int H = Integer.parseInt(st.nextToken());
        	int thr = R-H+1;
        	if (i%2 == 0) { // 왼쪽에서 던지기
        		for (int k = 1; k <= C; k++) {
        			if (board[thr][k] == 'x') {
        				board[thr][k] = '.';
        				process(R, C);
        				break;
        			}
        		}
        	} else { // 오룬쪽에서 던지기
        		for (int k = C; k >= 1; k--) {
        			if (board[thr][k] == 'x') {
        				board[thr][k] = '.';
        				process(R, C);
        				break;
        			}
        		}
        	}
        	
        }
        for (int m = 1; m <= R; m++) {
			for (int n = 1; n <= C; n++) {
				sb.append(board[m][n]);
			}
			if (m != R) {
				sb.append("\n");
			}
		}
		System.out.print(sb.toString().trim());
	}
	private static void process(int R, int C) {
		Queue<int[]> qu = new ArrayDeque<>();
		boolean[][] visited = new boolean[R+1][C+1];
		char[][] copy = new char[R+1][C+1];
		for (int i = 1; i <= R; i++) {
			copy[i] = board[i].clone();
		}
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (!visited[i][j] && copy[i][j] == 'x') {
					qu.offer(new int[] {i, j});
					visited[i][j] = true;
					List<int[]> cand = new ArrayList<>();
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						int x = tmp[0];
						int y = tmp[1];
						cand.add(new int[] {x, y});
						
						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if (!isBoundary(nx, ny, R, C) || visited[nx][ny] || copy[nx][ny] == '.') {
								continue;
							}
							
							visited[nx][ny] = true;
							qu.offer(new int[] {nx, ny});
						}
					}
					int tmp = R+1;
					for (int[] c: cand) {
						int x = c[0];
						int y = c[1];
						
						if (x == R) {
							tmp = 0;
							break;
						}
						if (copy[x+1][y] == 'x') {
							continue;
						}
						int cnt = -1;
						for (int k = 1; x + k <= R; k++) {
							if (copy[x+k][y] == 'x') {
								boolean flag = true;
								for (int[] a: cand) {
									if (a[0] == x+k && a[1] == y) {
										flag = false;
										break;
									}
								}
								if (flag) {
									cnt = k-1;
								}
								break;
							}
						}
						if (cnt == -1) {
							cnt = R - x;
						}
						tmp = Math.min(tmp, cnt);
					}
					//System.out.println(i + " " + j + " " + tmp);
					if (tmp != 0) {
						for (int[] c: cand) {
							board[c[0]][c[1]] = '.';
						}
						for (int[] c: cand) {
							board[c[0]+tmp][c[1]] = 'x';
						}
					}
				}
			}
		}
	}
	private static boolean isBoundary(int nx, int ny, int R, int C) {
		return nx >= 1 && nx <= R && ny >= 1 && ny <= C;
	}

}
