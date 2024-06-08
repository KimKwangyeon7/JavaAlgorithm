
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 3;
		for (int t = 0; t < T; t++) { // 테케 수만큼 반복
			int N = Integer.parseInt(br.readLine()); // 동전 종류
			List<int[]> coins = new ArrayList<>(); // 동전들을 담을 리스트
			boolean[] dp = new boolean[100001]; // 금액 체크할 배열
			int total = 0;
			
			for (int j = 0; j < N; j++) { // 동전 종류만큼 반복
				st = new StringTokenizer(br.readLine());
				int val = Integer.parseInt(st.nextToken()); // 동전의 종류
				int cnt = Integer.parseInt(st.nextToken()); // 동전의 개수
				coins.add(new int[] {val, cnt});
				total += val * cnt;
				for (int k = 0; k <= cnt; k++) {
					dp[val*k] = true;
				}
			}
			
			if ((total % 2) != 0) { // 금액의 총합이 짝수가 아니면
				System.out.println("0");
				continue;
			}
			if (dp[total/2]) { // 총합의 반이 이미 가능한 금액이면
				System.out.println("1");
				continue;
			}
			boolean flag = false;
			for (int i = 0; i < coins.size(); i++) {
				int val = coins.get(i)[0];
				int cnt = coins.get(i)[1];
				if (flag) {
					break;
				}
				for (int j = total/2; j >= val; j--) { // 내림차순으로
					if (dp[j-val]) {
						if (j == total/2) {
							System.out.println("1");
							flag = true;
							break;
						}
						for (int k = 1; k <= cnt; k++) {
							if (j-val + k*val > total/2) {
								break;
							}
							dp[j-val + k*val] = true;
						}
					}
				}
			}
			if (!flag) {
				if (dp[total/2]) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
			}
		}
	}
}
