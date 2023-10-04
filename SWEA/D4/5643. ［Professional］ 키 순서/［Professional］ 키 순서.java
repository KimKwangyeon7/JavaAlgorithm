import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Solution {
	
	static boolean[] visit;
	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			board = new int[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				
				board[a][b] = 1;
			}
			visit = new boolean[N];
			int cnt = 0;
			
			for (int i = 0; i < N; i++) {
				if (isTaller(i) + isSmaller(i) == N-1) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static int isSmaller(int x) {
		int cnt = 0;
		visit = new boolean[N];
		Queue<Integer> qu = new ArrayDeque<>();
		qu.offer(x);
		while(!qu.isEmpty()){
			int tmp = qu.poll();
			for (int k = 0; k < N; k++) {
				if (board[tmp][k] == 1 && !visit[k]) {
					visit[k] = true;
					qu.offer(k);
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static int isTaller(int x) {
		int cnt = 0;
		visit = new boolean[N];
		Queue<Integer> qu = new ArrayDeque<>();
		qu.offer(x);
		while(!qu.isEmpty()){
			int tmp = qu.poll();
			for (int k = 0; k < N; k++) {
				if (board[k][tmp] == 1 && !visit[k]) {
					visit[k] = true;
					qu.offer(k);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}