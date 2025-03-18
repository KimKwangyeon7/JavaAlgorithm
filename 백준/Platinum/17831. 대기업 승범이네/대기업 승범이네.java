
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
	static List<Integer>[] list;
	static int[] val;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		val = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			list[num].add(i);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			val[i] = num;
		}
		dp = new int[N+1][2];
		dfs(1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
//		for (int i = 1; i <= N; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
	}
	private static void dfs(int cur) {
		if (list[cur].isEmpty()) {
			return;
		}
		int extraCost = 0;
		for (int next: list[cur]) {
			dfs(next);
			// 멘토가 아닐 경우
			dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
			dp[cur][1] += Math.max(dp[next][0], dp[next][1]);
			// 멘토일 경우
			extraCost = Math.max(extraCost, dp[next][0] + val[next]*val[cur] - Math.max(dp[next][0], dp[next][1]));
		}
		dp[cur][1] += extraCost;
	}
}
