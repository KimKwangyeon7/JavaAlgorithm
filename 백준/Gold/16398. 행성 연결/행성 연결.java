

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
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 1; j <= N; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
    	Queue<int[]> qu = new PriorityQueue<>(new Comparator<int[]>() {
    		public int compare(int[] o1, int[] o2) {
    			return Long.compare(o1[1], o2[1]); 
    		}
    	});

    	boolean[] visited = new boolean[N+1];
    	//visited[1] = true;
    	qu.offer(new int[] {1, 0});
    	long totalCost = 0;
    	int cnt = 0;
    	
    	while (!qu.isEmpty()) {
    		int[] tmp = qu.poll();
    		
    		if (visited[tmp[0]]) {
    			continue;
    		}
    		visited[tmp[0]] = true;
    		totalCost += tmp[1];
    		cnt++;
    		
    		if (cnt == N) {
    			break;
    		}
    		
    		for (int i = 1; i <= N; i++) {
    			if (!visited[i]) {
    				qu.offer(new int[] {i, board[tmp[0]][i]});
    			}
    		}
    	}
    	System.out.println(totalCost);
    	
	}

}
