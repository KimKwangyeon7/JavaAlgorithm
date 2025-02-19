

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
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int INF = Integer.MAX_VALUE;

		int[][] dis = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dis[i], INF);
			dis[i][i] = 0;
		}
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == -1) {
				break;
			}
			dis[a][b] = 1;
			dis[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dis[i][k] != INF && dis[k][j] != INF) {
						dis[i][j] = Math.min(dis[i][j], dis[i][k]+dis[k][j]);
					}
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		int res = INF;
		for (int i = 1; i <= N; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					max = Math.max(dis[i][j], max);
				}
			}
			if (max < res) {
				list.clear();
				list.add(i);
				res = max;
			} else if (max == res) {
				list.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(res).append(" ").append(list.size()).append("\n");
		Collections.sort(list);
		for (int t: list) {
			sb.append(t + " ");
		}
		System.out.print(sb.toString());
	}
}
