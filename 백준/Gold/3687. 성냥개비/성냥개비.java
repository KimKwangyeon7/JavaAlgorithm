

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kwang
 * BJ_3687_성냥개비_김광연
 * 메모리 16268 kb 시간 132 ms
 * 아이디어
 * dp. 점화식 활용해 최대 최소값 구하는 문제
 * 최대값은 점화식 상관없이 짝수와 홀수 나눠서 구하기 가능
 * 최소값은 2부터 7개까지 성냥개비 쓸 때의 최소값을 구해두고 계속 더하가면서 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 테케 개수
		for (int i = 0; i < N; i++) { // 테케 수만큼 반복
			int cnt = Integer.parseInt(br.readLine());
			long[] dp = new long[101]; // 해당 성냥 개수로 만들 수 있는 최소값
			Arrays.fill(dp, Long.MAX_VALUE);
			dp[2] = 1;
			dp[3] = 7;
			dp[4] = 4;
			dp[5] = 2;
			dp[6] = 6;
			dp[7] = 8;
			dp[8] = 10; // 2부터 7까지의 값들을 써야하는데 7부터 시작하면 1일 때 값이 필요하므로 dp[8]의 값도 미리 구함
			if (cnt <= 8) {
				// 최소값 sb에 저장
				sb.append(dp[cnt]).append(" ");
				// 최대값 찾기
				if ((cnt % 2) == 0) { // 짝수면
					for (int k = 0; k < cnt/2; k++) {
						sb.append("1");
					}
					sb.append("\n");
				} else { // 홀수면
					sb.append("7");
					for (int k = 0; k < (cnt-1)/2-1; k++) {
						sb.append("1");
					}
					sb.append("\n");
				}
				continue;
			}
			dp[6] = 0; // 한자리수일 때를 제외하면 0으로 쓰는게 최소이므로 변경
			for (int j = 9; j <= 100; j++) { //9부터 차례차례 점화식으로 구하기
                for (int k = 2; k <= 7; k++) { // 2부터 7의 최소값은 고정이므로 이 값들 활용
                	if (dp[j-k] == 0) { // 0이 맨앞에 올 경우 6으로
                		// 이때,dp 값 자체를 바꿔주면 안되고 이 상황에서만 6으로 써야함
                		 String str = "" + "6" + dp[k];
                         dp[j] = Math.min(Long.parseLong(str), dp[j]); // 최소값을 저장
                         continue;
                    }	
                    String str = "" + dp[j - k] + dp[k];
                    //System.out.println(str);
                    dp[j] = Math.min(Long.parseLong(str), dp[j]); // 최소값을 저장
                	//System.out.println(dp[j]);
                }
                // 찾는 값과 같으면
                if (j == cnt) {
                	sb.append(dp[j]).append(" "); // sb에 저장하고 반복문 탈출
                	break;
                }
            }
			// 최대값 찾기
			if ((cnt % 2) == 0) { // 짝수면
				for (int k = 0; k < cnt/2; k++) {
					sb.append("1");
				}
				sb.append("\n");
			} else { // 홀수면
				sb.append("7");
				for (int k = 0; k < (cnt-1)/2-1; k++) {
					sb.append("1");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}
