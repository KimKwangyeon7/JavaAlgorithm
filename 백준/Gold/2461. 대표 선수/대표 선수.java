
import java.util.*;
import java.io.*;
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
		int M = Integer.parseInt(st.nextToken());
		int[][] board = new int[M*N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i*M+j][0] = Integer.parseInt(st.nextToken());
				board[i*M+j][1] = i;
			}
		}
		int answer = Integer.MAX_VALUE;
		Arrays.sort(board, (a, b) -> Integer.compare(a[0], b[0]));
		int left = 0;
		int right = 0;
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		while (left <= right && right < N*M) {
			int group = board[right][1];
			
			map.put(group, map.getOrDefault(group, 0) + 1);
			if (map.get(group) == 1) {
				cnt++;
			}
			
			while (cnt == N) {
				//System.out.println(board[right][0] + " " + board[left][0]);
				answer = Math.min(answer, board[right][0] - board[left][0]);
				map.put(board[left][1], map.get(board[left][1]) - 1);
				if (map.get(board[left][1]) == 0) {
					cnt--;
				}
				left++;
			}
			right++;
		}
		System.out.println(answer);
	}

}
