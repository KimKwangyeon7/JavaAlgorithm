

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
	static List<Integer>[] list;
	static List<Integer>[] child;
	static int[] degree;
	static Map<String, Integer> map;
	static Map<Integer, String> revMap;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new TreeMap<>();
		revMap = new HashMap<>();
		list = new ArrayList[N+1];
		child = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			String name = st.nextToken();
			map.put(name, i);
			revMap.put(i,  name);
			list[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		
		degree = new int[N+1];
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// X의 조상 중 Y가 있다
			String X = st.nextToken();
			String Y = st.nextToken();
			list[map.get(Y)].add(map.get(X));
			degree[map.get(X)]++;
		}
		
		Queue<int[]> qu = new ArrayDeque<>();
		int cnt = 0;
		List<String> res = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0 && !visited[i]) {
				cnt++;
				res.add(revMap.get(i));
				qu.offer(new int[] {i, -1});
				visited[i] = true;
				checkFamily(qu);
			}
		}
		sb.append(cnt).append("\n");
		Collections.sort(res);
		for (String r: res) {
			sb.append(r + " ");
		}
		sb.append("\n");
		for (String key: map.keySet()) {
			//System.out.print(key + " " + child[map.get(key)].size());
			sb.append(key + " ").append(child[map.get(key)].size());
			if (child[map.get(key)].isEmpty()) {
				sb.append("\n");
				continue;
			}
			List<String> tmp = new ArrayList<>();
			for (int t: child[map.get(key)]) {
				tmp.add(revMap.get(t));
			}
			Collections.sort(tmp);
			for (String t: tmp) {
				//System.out.println(" " + t);
				sb.append(" " + t);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void checkFamily(Queue<int[]> qu) {
		while (!qu.isEmpty()) {
			int[] cur = qu.poll();
			if (cur[1] != -1) {
				child[cur[1]].add(cur[0]);
			}
			if (list[cur[0]].isEmpty()) {
				continue;
			}
			for (int next: list[cur[0]]) {
				degree[next]--;
				if (degree[next] == 0) {
					qu.offer(new int[] {next, cur[0]});
					visited[next] = true;
				}
			}
		}
	}
}
