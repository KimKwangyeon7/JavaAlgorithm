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
	static class Node {
		int to;
		int dis;
		
		public Node(int to, int dis) {
			this.to = to;
			this.dis = dis;
		}
	}
	static List<Node>[] list;
    static boolean[] visited;
    static long maxDist = 0L;
    static int farNode = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		int[] degree = new int[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) {
					break;
				}
				int cost = Integer.parseInt(st.nextToken());
				list[from].add(new Node(num, cost));
				degree[i]++;
			}
		}
		// 1. 임의의 정점(1번)에서 가장 먼 노드를 찾음
        visited = new boolean[N + 1];
        dfs(1, 0L);

        // 2. 찾은 노드에서 다시 가장 먼 거리 탐색
        visited = new boolean[N + 1];
        maxDist = 0L;
        dfs(farNode, 0L);

        System.out.println(maxDist);
	}
	private static void dfs(int cur, long sum) {
		visited[cur] = true;

        if (sum > maxDist) {
            maxDist = sum;
            farNode = cur;
        }

        for (Node next : list[cur]) {
            if (!visited[next.to]) {
                dfs(next.to, sum + next.dis);
                visited[next.to] = true;
            }
        }
	}

}
