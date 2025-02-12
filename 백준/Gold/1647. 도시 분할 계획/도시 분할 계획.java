

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
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<int[]> edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new int[] {a, b, c});
		}
		
		long totalCost = 0;
		int cnt = 0;
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		edges.sort(Comparator.comparingInt(o -> o[2]));
		int maxEdge = 0;
		for (int[] edge: edges) {
			int a = edge[0];
			int b = edge[1];
			int cost = edge[2];
			
			if (union(a, b)) {
				//System.out.println(a + " " + b + " " + cost);
				totalCost += cost;
				maxEdge = cost;
				cnt++;
			}
			if (cnt == N-1) {
				break;
			}
 		}
		System.out.println(totalCost-maxEdge);
		
	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	private static boolean union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) {
			return false;
		}
		if (rootX < rootY) {
			parent[rootY] = rootX;
		} else {
			parent[rootX] = rootY;
		}
		return true;
	}
}
