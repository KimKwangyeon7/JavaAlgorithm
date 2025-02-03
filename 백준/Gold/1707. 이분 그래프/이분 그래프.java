

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
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			List<Integer>[] list = new ArrayList[V+1];
			for (int i = 0; i < V+1; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			
			int[] color = new int[V+1];
			int flag = 0;
			for (int j = 1; j < V+1; j++) {
				if (color[j] != 0) {
					continue;
				}
				Queue<Integer> qu = new ArrayDeque<>();
				qu.offer(j);
				color[j] = 1;
				while (!qu.isEmpty()) {
					int tmp = qu.poll();
					
					for (int i = 0; i < list[tmp].size(); i++) {
						int next = list[tmp].get(i);
						if (color[next] == 0) {
							color[next] = color[tmp] * (-1);
							qu.offer(next);
						} else if (color[next] == color[tmp]) {
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			
			if (flag == 0) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}

}
