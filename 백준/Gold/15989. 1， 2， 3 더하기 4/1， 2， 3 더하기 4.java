

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author kwang
 * BJ_15989_1_2_3더하기4_김광연 
 * 메모리 12152 kb 시간 84 ms
 * 아이디어
 * dp 문제 (점화식)
 * 특정 수를 1, 2, 3의 합으로 나타내기 => 중복 X(순서없음)
 * 수식의 마지막 숫자를 기준으로 점화식
 * dp[i][j] = i는 나타내려는 수, j는 수식의 마지막 숫자 => 3가지로 나눠서 더하기							
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // StringBuilder 활용해 시간 복잡도 줄이기
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수 
		int[][] dp = new int[10001][4];
		// 1, 2, 3일 때 나올 수 있는 경우의 수
		dp[1][1] = 1; // 1
		dp[2][1] = 1; // 1+1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 1+1+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3
		
		for(int i = 4; i <= 10000; i++) {
			// 수식이 1로 끝나는 경우 => 1만 더하면 같음
			dp[i][1] = dp[i-1][1];
			// 2로 끝나는 경우 => 1을 더할 때와 2를 더할 때
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			// 3으로 끝나는 경우 => 1, 2, 3을 더할 때
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine()); // 수식으로 표현할 수
			sb.append(dp[N][1] + dp[N][2] + dp[N][3]).append("\n");
		}
		System.out.println(sb);
	}

}
