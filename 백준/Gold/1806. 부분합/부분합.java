

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		N = 100000;
//		int[] board = new int[N];
//		S = 100000000;
//		
//		for (int i = 0; i < N; i++) {
//			board[i] = (123*(i+1)) % 10001;
//		}
		
		int min = Integer.MAX_VALUE;
		long sum = 0;
		int left = 0;
		int right = 0;
		int cnt = 0;
//		if (arr[N-1] >= S) {
//			System.out.println(1);
//			return;
//		}
		while (right <= N) {
			if (sum < S) {
				sum += arr[right++];
				//sum += board[right++];
				cnt++;
			} else {
				sum -= arr[left++];
				//sum -= board[left++];
				cnt--;
			} 
			if (sum >= S) {
				min = Math.min(min, cnt);
			}
		}
		if (min == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(min);
		}
	}
}
