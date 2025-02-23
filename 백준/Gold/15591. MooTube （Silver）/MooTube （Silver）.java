

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        List<int[]>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int u = Integer.parseInt(st.nextToken());
        	list[a].add(new int[] {b, u});
        	list[b].add(new int[] {a, u});
        }
        
        for (int i = 0; i < Q; i++) {
        	st = new StringTokenizer(br.readLine());
        	int k = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	
        	Queue<Integer> qu = new ArrayDeque<>();
        	qu.offer(v);
        	boolean[] visited = new boolean[N+1];
        	visited[v] = true;
        	int cnt = 0;
        	while (!qu.isEmpty()) {
        		int tmp = qu.poll();
        		for (int[] next: list[tmp]) {
        			if (visited[next[0]] || next[1] < k) {
        				continue;
        			}
        			qu.offer(next[0]);
        			visited[next[0]] = true;
        			cnt++;
        		}
        	}
        	sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString().trim());
	}
}
