

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static int N, C;
	static long[] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new long[N];
		for (int i = 0; i < N; i++) {
			board[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(board);
		
		long left = 0;
		long right = 1000000000;
		long answer = 0;
		while (left <= right) {
			long mid = (left+right) / 2;
			if (check(mid)) {
				answer = Math.max(answer, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}
	private static boolean check(long x) {
		if (board[N-1] < x) {
			return false;
		}
		int cnt = 1;
		long dis = board[0] + x;
		
		for (int i = 1; i < N; i++) {
			if (board[i] >= dis) {
				dis = board[i] + x;
				cnt++;
				if (cnt == C) {
					return true;
				}
			} 
		}
		return false;
	}
}
