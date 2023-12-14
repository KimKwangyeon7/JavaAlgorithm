
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] height = new int[W];
		int[][] board = new int[H][W];
		int max = -1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			int h = height[i];
			max = Math.max(max, h);
			for (int j = H-h; j < H; j++) {
				board[j][i] = 1;
			}
		}
		
		
//		for (int i = 0; i <  H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}
		
		int flag = 0;
		int cnt = 0;
		int sum = 0;
		for (int i = H-max; i < H; i++) {
			flag = 0;
			cnt = 0;
			for (int j = 0; j < W; j++) {
				if (board[i][j] == 0 && flag == 0) {
					continue;
				} else if (board[i][j] == 1 && flag == 0) {
					flag = 1;
				} else if (board[i][j] == 0 && flag == 1) {
					cnt++;
					board[i][j] = 2;
				} else {
					sum += cnt;
					cnt = 0;
				}
			}
		}
//		for (int i = 0; i <  H; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(board[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(sum);
		
		
	}

}
