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
		int N = Integer.parseInt(br.readLine());
		
		boolean[] dis = new boolean[N+1];
		List<Integer> list = new ArrayList<>();
		for (int i = 2; i <= N; i++) {
			if (!dis[i]) {
				list.add(i);
				for (int j = i*2; j <= N; j += i) {
					dis[j] = true; // true면 소수가 아님. 
				}
			}
		}
		System.out.println(check(list, N));
	}

	private static int check(List<Integer> list, int target) {
		int ans = 0;
		int left = 0;
		int len = list.size();
		int right = 0;
		if (list.contains(target)) {
			ans++;
		}
		int sum = 0;
		while (left < len && right < len) {
			if (sum < target) {
				sum += list.get(right++);
			} else if (sum > target) {
				sum -= list.get(left++);
			} else {
				ans++;
				//System.out.println(list.get(left) + " " + list.get(right));
				sum -= list.get(left++);
			}
		}
		
		return ans;
		
		
	}

}
