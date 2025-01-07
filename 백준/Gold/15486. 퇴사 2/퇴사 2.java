
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] t = new int[N + 1];
        int[] p = new int[N + 1];
        long[] dp = new long[N + 2]; // N+1일째까지의 최대 수익 저장

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            // 이전까지의 최대 수익을 현재에도 유지
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 상담 완료 후의 날짜가 범위를 초과하지 않는 경우 수익 갱신
            if (i + t[i] <= N + 1) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
        }

        // 최대 수익 출력
        System.out.println(dp[N + 1]);
    }
}
