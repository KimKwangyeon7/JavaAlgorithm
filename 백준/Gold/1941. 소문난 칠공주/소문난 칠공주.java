

import java.io.*;
import java.util.*;

public class Main {
	static char[][] board;
	static int ans;
	static List<int[]> list;
	static int[] res;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[5][5];
		list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				board[i][j] = str.charAt(j);
				list.add(new int[] {i, j});
			}
		}
		
		int len = list.size();
		if (len < 4) {
			System.out.println(0);
		} else {
			ans = 0;
			res = new int[7];
			combi(0, 0, 7, len);
			System.out.println(ans);
		}
		
	}
	private static void combi(int L, int start, int N, int len) {
		if (L == N) {
			//System.out.println(res[0] + " " + res[1] + " " + res[2] + " " + res[3]);
			int[] tmp = res.clone();
			if (check(tmp)) {
				ans++;
			}
			return;
		}
		for (int i = start; i < len; i++) {
			res[L] = i;
			combi(L+1, i+1, N, len);
		}
	}
	private static boolean check(int[] tmp) {
		boolean[][] visited = new boolean[5][5];
		int startX = list.get(tmp[0])[0];
		int startY = list.get(tmp[0])[1];
		visited[startX][startY] = true;
		
		Queue<int[]> qu = new ArrayDeque<>();
		qu.offer(new int[] {startX, startY});
		int total = 1;
		int ds = 0;
		if (board[startX][startY] == 'S') {
			ds++;
		}
		
		while (!qu.isEmpty()) {
			int[] node = qu.poll();
			
			for (int k = 0; k < 4; k++) {
				int x = node[0] + dx[k];
				int y = node[1] + dy[k];
				
				if (!isBoundary(x, y) || visited[x][y]) {
					continue;
				}
				int flag = 0;
				for (int i = 0; i < res.length; i++) {
					int[] r = list.get(tmp[i]);
					if (x == r[0] && y == r[1]) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					visited[x][y] = true;
					total++;
					qu.offer(new int[] {x, y});
					if (board[x][y] == 'S') {
						ds++;
					}
				} else {
					continue;
				}
			}
		}
		if (total < 7) {
			return false;
		} else {
			if (ds >= 4) {
				return true;
			}
			return false;
		}
		//dfs(startX, startY, 1, 1, visited, tmp);
	}
//	private static void dfs(int startX, int startY, int prinCnt, int L, boolean[][] visited, int[] tmp) {
//		if (L == 7) {
//			if (prinCnt == 4) {
//				System.out.println(startX + " " + startY);
//				ans++;
//			}
//			return;
//		}
//		for (int k = 0; k < 4; k++) {
//			int x = startX + dx[k];
//			int y = startY + dy[k];
//			
//			if (!isBoundary(x, y) || visited[x][y]) {
//				continue;
//			}
//			visited[x][y] = true;
//			int flag = 0;
//			for (int i = 0; i < tmp.length; i++) {
//				int xx = list.get(tmp[i])[0];
//				int yy = list.get(tmp[i])[1];
//				
//				if (x == xx && y == yy) {
//					flag = 1;
//					break;
//				}
//			}
//			if (flag == 1) {
//				dfs(x, y, prinCnt+1, L+1, visited, tmp);
//				visited[x][y] = false;
//			} else {
//				dfs(x, y, prinCnt, L+1, visited, tmp);
//				visited[x][y] = false;
//			}
//		}
//	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}
}
