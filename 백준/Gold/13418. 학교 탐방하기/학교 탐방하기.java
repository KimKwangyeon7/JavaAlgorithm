import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++) {
        	Arrays.fill(board[i], -1);
        }
        int tmp = 0;
        for (int i = 0; i <= M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int info = Integer.parseInt(st.nextToken());
        	board[a][b] = info;
        	board[b][a] = info;
        	if (i == 0) {
        		tmp = info;
        	}
        }
        
        Queue<int[]> qu = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        Queue<int[]> qu2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        qu.offer(new int[] {0, -1});
        boolean[] visited = new boolean[N+1];
        int cnt = 0;
        int up = 0;
        
        while (!qu.isEmpty()) {
        	int[] cur = qu.poll();
        	//System.out.println(cur[0] + " " + cur[1]);
        	if (visited[cur[0]]) {
        		continue;
        	}
        	visited[cur[0]] = true;
        	cnt++;
        	//up += (cur[1] == 0) ? 1: 0;
        	if (cur[1] == 0) {
        		up++;
        	}
        	if (cnt == N+1) {
        		break;
        	}
        	
        	for (int i = 1; i <= N; i++) {
        		if (!visited[i] && board[cur[0]][i] != -1) {
        			qu.offer(new int[] {i, board[cur[0]][i]});
        		}
        	}
        }
        int min = (int) Math.pow(up, 2);
        qu2.offer(new int[] {0, -1});
        visited = new boolean[N+1];
        cnt = 0;
        up = 0;
        while (!qu2.isEmpty()) {
        	int[] cur = qu2.poll();
        	//System.out.println(cur[0] + " " + cur[1]);
        	if (visited[cur[0]]) {
        		continue;
        	}
        	visited[cur[0]] = true;
        	cnt++;
        	//up += (cur[1] == 0) ? 1: 0;
        	if (cur[1] == 0) {
        		up++;
        	}
        	if (cnt == N+1) {
        		break;
        	}
        	
        	for (int i = 1; i <= N; i++) {
        		if (!visited[i] && board[cur[0]][i] != -1) {
        			qu2.offer(new int[] {i, board[cur[0]][i]});
        		}
        	}
        }
        int max = (int) Math.pow(up, 2);
        System.out.println(max-min);
	}

}
