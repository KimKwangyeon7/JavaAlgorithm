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
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] board = new long[N];
        for (int i = 0; i < N; i++) {
        	board[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(board);
        long[][] dp = new long[N][N];
        boolean flag = false;
        for (int k = N-1; k >= 1; k--) {
        	for (int i = 0; i < k; i++) {
        		for (int j = i; j < k; j++) {
    				long target = board[k] - board[i] - board[j];
    				if (binarySearch(target, j, k, board)) {
    					//System.out.println(board[i] + " " + board[j] + " " + target + " " + board[k]);
    					System.out.println(board[k]);
    					flag = true;
    					break;
    				}
        		}
        		if (flag) {
        			k = 0;
        			break;
        		}
        	}
        }
	}

	private static boolean binarySearch(long target, int i, int k, long[] board) {
		int left = i;
		int right = k-1;
		while (left <= right) {
			int mid = (left+right) / 2;
			if (board[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (left < 0 || left >= board.length || board[left] != target) {
			return false;
		}
		return true;
	}

}
