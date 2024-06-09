
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
	static int N, max, min;
	static int[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		nums = new int[N]; // 수 저장할 배열
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ops = new int[4]; // 연산자 개수 저장할 배열 (+, -, *, /)
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(0, ops, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	static void dfs(int L, int[] ops, int sum) {
		if (L == N-1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
		}
		if (ops[0] != 0) { // 덧셈
			ops[0]--;
			dfs(L+1, ops, sum+nums[L+1]);
			ops[0]++;
		}
		if (ops[1] != 0) { // 뺄셈
			ops[1]--;
			dfs(L+1, ops, sum-nums[L+1]);
			ops[1]++;
		}
		if (ops[2] != 0) { // 곱셈
			ops[2]--;
			dfs(L+1, ops, sum*nums[L+1]);
			ops[2]++;
		}
		if (ops[3] != 0) { // 나눗셈
			ops[3]--;
			dfs(L+1, ops, sum/nums[L+1]);
			ops[3]++;
		}
	}
}
