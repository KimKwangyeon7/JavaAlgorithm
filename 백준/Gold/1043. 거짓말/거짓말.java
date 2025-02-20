
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
        List<Integer>[] person = new ArrayList[N+1];
        //List<Integer>[] party = new ArrayList[M+1];
        List<Integer>[] party = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
        	person[i] = new ArrayList<>();
        	party[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
        	int a = Integer.parseInt(st.nextToken());
        	person[a].add(0);
        	person[0].add(a);
        }
        for (int i = 1; i <= M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int t = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
        	party[start].add(i);
        	for (int j = 1; j < t; j++) {
        		int tmp = Integer.parseInt(st.nextToken());
        		party[tmp].add(i);
        		
        		person[start].add(tmp);
        		person[tmp].add(start);
        	}
        }
        
        Queue<Integer> qu = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        qu.offer(0);
        visited[0] = true;
        
        while (!qu.isEmpty()) {
        	int now = qu.poll();
        	
        	for (int next: person[now]) {
        		if (!visited[next]) {
        			visited[next] = true;
        			qu.offer(next);
        		}
        	}
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
        	if (!visited[i]) {
        		for (int p: party[i]) {
        			set.add(p);
        		}
        	}
        }
        System.out.println(set.size());
	}

}
