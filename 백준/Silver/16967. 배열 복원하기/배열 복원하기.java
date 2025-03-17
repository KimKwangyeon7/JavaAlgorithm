
/**
 * @author kwang
 *
 */
import java.util.*;
import java.io.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[H+X][W+Y];
		int[][] ans = new int[H][W];
		for (int i = 0; i < H+X; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W+Y; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if ((i >= X && i <= H-1) && (j >= Y && j <= W-1)) {
					ans[i][j] = board[i][j] - ans[i-X][j-Y];
					sb.append(ans[i][j]).append(" ");
				} else {
					ans[i][j] = board[i][j];
					sb.append(board[i][j]).append(" ");
				}
			}
			sb.append("\n");
		} 
		System.out.println(sb.toString().trim());
	}

}
