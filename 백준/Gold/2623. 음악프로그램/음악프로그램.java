

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
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		int[] degree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			if (cnt == 0) {
				continue;
			}
			int prev = Integer.parseInt(st.nextToken());
			for (int j = 1; j < cnt; j++) {
				int next = Integer.parseInt(st.nextToken());
				list[prev].add(next);
				prev = next;
				degree[next]++;
			}
		}
		
		Queue<Integer> qu = new ArrayDeque<>();
		List<Integer> res = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				qu.offer(i);
			}
		}
		
		while (!qu.isEmpty()) {
			int cur = qu.poll();
			res.add(cur);
			//System.out.println(cur);
			if (list[cur].size() == 0) {
				continue;
			}
			for (int next: list[cur]) {
				degree[next]--;
				if (degree[next] == 0) {
					qu.offer(next);
				}
			}
		}
		
		if (res.size() != N) {
			System.out.println(0);
		} else {
			for (int r: res) {
				System.out.println(r);
			}
		}
	}

}
