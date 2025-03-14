import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int N = info.length;
        int INF = Integer.MAX_VALUE / 2; // 오버플로 방지용
        
        // DP 배열 정의: dp[i][a][b] = i번째 물건까지 고려했을 때 (A 흔적 a, B 흔적 b)의 최소 A 흔적 값
        int[][][] dp = new int[N + 1][n + 1][m + 1];

        // DP 배열 초기화
        for (int i = 0; i <= N; i++) {
            for (int a = 0; a <= n; a++) {
                Arrays.fill(dp[i][a], INF);
            }
        }

        dp[0][0][0] = 0; // 초기 상태 (아무 물건도 안 훔친 상태)

        // DP 수행
        for (int i = 1; i <= N; i++) {
            int a = info[i - 1][0], b = info[i - 1][1]; // i번째 물건의 A, B 흔적
            
            for (int curA = 0; curA <= n; curA++) {
                for (int curB = 0; curB <= m; curB++) {
                    if (dp[i - 1][curA][curB] == INF) continue; // 이전 상태가 불가능하면 패스

                    // A 도둑이 훔치는 경우
                    int newA = Math.min(n, curA + a);
                    dp[i][newA][curB] = Math.min(dp[i][newA][curB], dp[i - 1][curA][curB] + a);

                    // B 도둑이 훔치는 경우
                    int newB = Math.min(m, curB + b);
                    dp[i][curA][newB] = Math.min(dp[i][curA][newB], dp[i - 1][curA][curB]);
                }
            }
        }

        // 최소 A 흔적 찾기
        int answer = INF;
        for (int curA = 0; curA < n; curA++) {
            for (int curB = 0; curB < m; curB++) {
                answer = Math.min(answer, dp[N][curA][curB]);
            }
        }

        return answer == INF ? -1 : answer;
    }
}
