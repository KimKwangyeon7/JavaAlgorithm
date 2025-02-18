

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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		board = new char[N+1][N+1];
		int tmp = N;
		int cnt = 0;
		while (tmp > 1) {
			tmp /= 3;
			cnt++;
		}
		print(cnt, N, N);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (board[i][j] != '*') {
					sb.append(" ");
				} else {
					sb.append(board[i][j]);
				}
			}
			if (i != N) {
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	private static void print(int cnt, int x, int y) {
		//System.out.println(cnt + " " + x + " " + y + " ");
		if (cnt == 0) {
			board[x][y] = '*';
			return;
		} else {
			int nx = (int)Math.pow(3, cnt-1);
			int ny = (int)Math.pow(3, cnt-1);
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1) {
						continue;
					}
					print(cnt-1, x-nx*i, y-ny*j);
				}
			}
		}
	}
}
