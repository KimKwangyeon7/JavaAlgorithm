

import java.io.*;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {
	static int[] dx = new int[] {1, 0, -1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] board = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 6; j++) {
				board[i][j] = tmp.charAt(j);
			}
		}
		int ans = 0;
		while (true) {
//			for (int i = 0; i < 12; i++) {
//				for (int j = 0; j < 6; j++) {
//					System.out.print(board[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			boolean[][] visited = new boolean[12][6];
			List<int[]> cand = new ArrayList<>();
			int flag = 0;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (board[i][j] != '.' && !visited[i][j]) {
						Queue<int[]> qu = new ArrayDeque<>();
						char color = board[i][j];
						visited[i][j] = true;
						qu.offer(new int[] {i, j});
						List<int[]> list = new ArrayList<>();
						list.add(new int[] {i, j});
						//int total = 0;
						while (!qu.isEmpty()) {
							int[] tmp = qu.poll();
							for (int k = 0; k < 4; k++) {
								int xx = tmp[0] + dx[k];
								int yy = tmp[1] + dy[k];
								
								if (!isBoundary(xx, yy) || visited[xx][yy] || board[xx][yy] != color) {
									continue;
								}
								visited[xx][yy] = true;
								qu.offer(new int[] {xx, yy});
								list.add(new int[] {xx, yy});
							}
						}
						if (list.size() >= 4) {
							flag = 1;
							cand.addAll(list);
						}
					}
				}
			}
			Set<Integer> set = new HashSet<>();
			for (int[] t: cand) {
				board[t[0]][t[1]] = '.';
				set.add(t[1]);
			}
//			for (int i = 0; i < 12; i++) {
//				for (int j = 0; j < 6; j++) {
//					System.out.print(board[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			for (int s: set) {
				int cnt = 0;
				for (int m = 11; m >= 0; m--) {
					if (board[m][s] == '.') {
						cnt++;
					} else {
						if (cnt != 0) {
							board[m+cnt][s] = board[m][s];
							board[m][s] = '.';
						}
					}
				}
			}
			if (flag == 1) {
				ans++;
			} else {
				break;
			}
		}
		System.out.println(ans);
	}
	private static boolean isBoundary(int xx, int yy) {
		return xx >= 0 && xx < 12 && yy >= 0 && yy < 6;
	}

}
