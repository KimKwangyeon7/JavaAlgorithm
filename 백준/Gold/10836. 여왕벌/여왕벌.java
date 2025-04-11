
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());

	        int M = Integer.parseInt(st.nextToken());
	        int N = Integer.parseInt(st.nextToken());

	        int[] growth = new int[2 * M - 1];

	        for (int d = 0; d < N; d++) {
	            st = new StringTokenizer(br.readLine());
	            int zero = Integer.parseInt(st.nextToken());
	            int one = Integer.parseInt(st.nextToken());
	            int two = Integer.parseInt(st.nextToken());

	            int idx = 0;
	            for (int i = 0; i < zero; i++) growth[idx++] += 0;
	            for (int i = 0; i < one; i++) growth[idx++] += 1;
	            for (int i = 0; i < two; i++) growth[idx++] += 2;
	        }

	        int[][] board = new int[M][M];
	        // 왼쪽 열
	        for (int i = M - 1; i >= 0; i--) {
	            board[i][0] = 1 + growth[M - 1 - i];
	        }
	        // 위쪽 행
	        for (int j = 1; j < M; j++) {
	            board[0][j] = 1 + growth[M - 1 + j];
	        }

	        for (int i = 1; i < M; i++) {
	            for (int j = 1; j < M; j++) {
	                board[i][j] = Math.max(board[i - 1][j], Math.max(board[i][j - 1], board[i - 1][j - 1]));
	            }
	        }

	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < M; i++) {
	            for (int j = 0; j < M; j++) {
	                sb.append(board[i][j]).append(" ");
	            }
	            sb.append("\n");
	        }
	        System.out.print(sb);
	    }
	}