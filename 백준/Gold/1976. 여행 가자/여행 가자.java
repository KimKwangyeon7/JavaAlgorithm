
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	static int[][] board;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] plan = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		int len = plan.length;
		int flag = 0;
		for (int i = 0; i < len-1; i++) {
			if (board[plan[i]][plan[i+1]] == 1 || plan[i] == plan[i+1]) {
				continue;
			}
			if (!isAlright(plan[i], plan[i+1])) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
		
	}
	static boolean isAlright(int start, int end) {
		 boolean[] visit = new boolean[N];
		 Queue<Integer> qu = new ArrayDeque<>();
		 qu.offer(start);
		 visit[start] = true;
		 while (!qu.isEmpty()) {
			 int tmp = qu.poll();
			 for (int i = 0; i < N; i++) {
				 if (board[tmp][i] == 0 || visit[i]) {
					 continue;
				 }
				 if (i == end) {
					 return true;
				 }
				 visit[i] = true;
				 qu.offer(i);
			 }
		 }
		 return false;
	}

}
