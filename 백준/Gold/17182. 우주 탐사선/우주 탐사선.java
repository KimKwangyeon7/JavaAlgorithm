
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static boolean[] visit;
	static int ans;
	static int N, K;
	static int[][] board;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == k || j == k || i == j) { // 출발지와 도착지가 같으면 다음 경우로 넘어가기
						continue;
					}
					board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
				}
			}
		}
		visit = new boolean[N];
        visit[K] = true;
        ans = Integer.MAX_VALUE;
        dfs(1, K, 0);
        System.out.println(ans);
	}
	private static void dfs(int L, int p, int sum) {
		if (L == N) {
            ans = Math.min(ans, sum);
        }
        
        for (int i = 0; i < N; i++) {
            if (visit[i]) {
            	continue;
            }
            visit[i] = true;
            dfs(L+1, i, sum + board[p][i]);
            visit[i] = false;
        }
	}

}
