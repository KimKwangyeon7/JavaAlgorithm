

/**
 * @author kwang
 *
 */
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int order = (N-1)/2;
		int ans = 0;
		List<Integer>[] big = new ArrayList[N+1];
		List<Integer>[] small = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			big[i] = new ArrayList<>();
			small[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			big[a].add(b);
			small[b].add(a);
		}
		for (int i = 1; i < N+1; i++) {
			Queue<Integer> qu = new ArrayDeque<>();
			qu.offer(i);
			int bigger = 0;
			int smaller = 0;
			boolean[] visited = new boolean[N+1];
			//visited[i] = true;
			while (!qu.isEmpty()) {
				int tmp = qu.poll();
				//System.out.println(tmp + " " + i);
				if (big[tmp].isEmpty()) {
					continue;
				}
				for (int j = 0; j < big[tmp].size(); j++) {
					int next = big[tmp].get(j);
					//System.out.println(next+" " + visited[next] + " " + i);
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					qu.offer(next);
					bigger++;
				}
			}
			
			visited = new boolean[N+1];
			qu.offer(i);
			//visited[i] = true;
			while (!qu.isEmpty()) {
				int tmp = qu.poll();
				
				if (small[tmp].isEmpty()) {
					continue;
				}
				for (int j = 0; j < small[tmp].size(); j++) {
					int next = small[tmp].get(j);
					if (visited[next]) {
						continue;
					}
					visited[next] = true;
					qu.offer(next);
					smaller++;
				}
			}
			//System.out.println(i + " " + bigger + " " + smaller);
			if (order < bigger || order >= N-smaller) {
				ans++;	
			}
		}
		System.out.println(ans);
	}

}
