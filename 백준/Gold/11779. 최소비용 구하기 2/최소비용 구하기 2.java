

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
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<int[]>[] list = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b, cost});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 최소 비용 저장 배열
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        // 경로 추적 배열 (이전 노드 저장)
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        // 우선순위 큐 (비용 오름차순 정렬)
        PriorityQueue<int[]> qu = new PriorityQueue<>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return Integer.compare(o1[1], o2[1]);
        	}
        });
        qu.offer(new int[]{start, 0});
        
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			int now = tmp[0];
			int nowCost = tmp[1];
			if (nowCost > dis[now]) {
				continue;
			}
			if (list[now].isEmpty()) {
				continue;
			}
			for (int[] li: list[now]) {
				int next = li[0];
				if (dis[next] > nowCost + li[1]) {
					dis[next] = nowCost + li[1];
					parent[next] = now;
					qu.offer(new int[] {next, dis[next]});
				}
			}
		}
		System.out.println(dis[end]);
		
//		for (int i = 1; i <= n; i++) {
//			System.out.print(parent[i] + " ");
//		}
//		System.out.println();
		// 경로 역추적
        List<Integer> path = new ArrayList<>();
        for (int i = end; i != -1; i = parent[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        // 경로 출력
        System.out.println(path.size());
        for (int node : path) {
            System.out.print(node + " ");
        }
	}

}
