
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
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static List<int[]> list;
	static double[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		dp = new double[N][1<<N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new int[] {x, y});
		}
		
		System.out.println(tsp(0, 1<<0));
		
	}
	private static double tsp(int cur, int visited) {
		if (visited == (1 << N) - 1) {
			return dis(cur, 0);
		}
		if (dp[cur][visited] != -1) {
			return dp[cur][visited];
		}
		
		double sum = INF;
		for (int i = 0; i < N; i++) {
			if (cur != i && (visited & (1 << i)) == 0) {
				double cost = dis(cur, i) + tsp(i, visited | (1 << i));
				sum = Math.min(sum, cost);
			}
		}
		return dp[cur][visited] = sum;
	}
	private static double dis(int from, int to) {
		return Math.sqrt(Math.pow(list.get(to)[1] - list.get(from)[1], 2) + Math.pow(list.get(to)[0] - list.get(from)[0], 2));
	}
}
