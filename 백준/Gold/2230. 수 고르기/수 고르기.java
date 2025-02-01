

import java.util.*;
import java.io.*;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] board = new long[N];
		for (int i = 0; i < N; i++) {
			board[i] = Long.parseLong(br.readLine());
		}
		
//		N = 100000;
//		M = 200000000;
//		long[] boardd = new long[N];
//		for (int i = 0; i < N; i++) {
//			boardd[i] = -1000000000 + i * 20000;
//		}
//		
		
		Arrays.sort(board);
		int left = 0;
		int right = 0;
		long ans = Long.MAX_VALUE;
		
		while (left <= right && right < N && left >= 0) {
			long cha = Math.abs(board[right] - board[left]);
			if (cha < M) {
				right++;
			} else if (cha == M) {
				ans = M;
				break;
			} else {
				ans = Math.min(ans, cha);
				left++;
			}
		}
		System.out.println(ans);
	}

}
