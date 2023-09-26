import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int[] memo;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[] board = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				board[i] = Integer.parseInt(st.nextToken());
			}
			memo = new int[N];
			memo[0] = board[0];
			cnt = 1;
			for (int i = 1; i < N; i++) {
				if (board[i] > memo[cnt-1]) {
					memo[cnt] = board[i];
					cnt++;
				} else {
					binarySearch(0, cnt, board[i]);
					
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	static void binarySearch(int start, int end, int target) {
		int mid = 0;
		while (start < end) {
			mid = (start + end) / 2;
			if (memo[mid] > target) {
				end = mid;
			} else if (memo[mid] == target) {
            	return;
            } else {
				start = mid+1;
			} 
		}
		if (target > memo[start]) {
			memo[start+1] = target;
		} else if (target == memo[start]) {
			return;
		} else {
			memo[start] = target;
		}
	}
}
