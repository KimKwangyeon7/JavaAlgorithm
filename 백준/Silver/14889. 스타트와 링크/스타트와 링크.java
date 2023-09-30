import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static int N;
	static int[] res;
	static int[] another;
	static int[][] board;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		res = new int[N/2];
		ans = 2147000000;
		combi(0, N/2, 0);
		System.out.println(ans);
	}

	private static void combi(int cnt, int size, int start) {
		if (cnt == size) {
			another = new int[N/2];
			int idx = 0;
			int flag = 0;
			for (int i = 0; i < N; i++) {
				flag = 0;
				for (int j: res) {
					if (i == j) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					another[idx++] = i;
				}
			}
//			System.out.println("res");
//			for (int i = 0; i < N/2; i++) {
//				System.out.print(res[i] + " ");
//			}
//			System.out.println();
//			System.out.println("another");
//			for (int i = 0; i < N/2; i++) {
//				System.out.print(another[i] + " ");
//			}
			ans = Math.min(ans, Math.abs(getSum(res) - getSum(another)));
			return;
		}
		for (int i = start; i < N; i++) {
			res[cnt] = i;
			combi(cnt+1, size, i+1);
		}
	}
	private static int getSum(int[] a) {
		int len = a.length;
		int sum = 0;
		for (int i = 0; i < len-1; i++) {
			for (int j = i+1; j < len; j++) {
				sum += board[a[i]][a[j]];
				sum += board[a[j]][a[i]];
			}
		}
		return sum;
	}

}
