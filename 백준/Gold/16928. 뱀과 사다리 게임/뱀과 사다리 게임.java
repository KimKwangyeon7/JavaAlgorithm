

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static int N, M;
	static int answer;
	static Map<Integer, Integer> map;
	static HashSet<Integer> set;
	static List<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		set = new HashSet<>();
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(x);
			map.put(x, y);
		}
		Collections.sort(list);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			set.add(u);
			map.put(u, v);
		}
		answer = Integer.MAX_VALUE;
		bfs();
		System.out.println(answer);
		
	}
	
	private static void bfs() {
		Queue<int[]> qu = new ArrayDeque<>();
		qu.offer(new int[] {0, 1});
		
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			int cur = tmp[1];
			int cnt = tmp[0];
			
			if (cur == 100) {
				answer = cnt;
				break;
			}
			
			boolean flag = true;
			for (int i = 6; i >= 1; i--) {
				int next = cur+i;
				if (map.containsKey(next)) {
					qu.offer(new int[] {cnt+1, map.get(next)});
				} else if (set.contains(next)) {
					continue;
				} else {
					if (flag) {
						if (next > 100) {
							continue;
						}
						flag = false;
						qu.offer(new int[] {cnt+1, next});
					} else {
						continue;
					}
				}
			}
		}
	}

}
