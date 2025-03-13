import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        
        // 문제에서 요구하는 최대 알고력과 코딩력을 찾기
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        // 현재 능력이 이미 문제를 풀 수 있는 최대 능력보다 크다면 줄여줌
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        // DP 배열 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[alp][cop] = 0;  // 시작 지점의 시간은 0

        // DP 수행
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                // 현재 상태의 최단 시간
                int curTime = dp[a][c];

                // 1. 알고력 증가
                if (a + 1 <= maxAlp) dp[a + 1][c] = Math.min(dp[a + 1][c], curTime + 1);

                // 2. 코딩력 증가
                if (c + 1 <= maxCop) dp[a][c + 1] = Math.min(dp[a][c + 1], curTime + 1);

                // 3. 문제 풀기
                for (int[] p : problems) {
                    int reqAlp = p[0], reqCop = p[1], rwdAlp = p[2], rwdCop = p[3], cost = p[4];

                    if (a >= reqAlp && c >= reqCop) { // 문제를 풀 수 있는 경우
                        int newAlp = Math.min(a + rwdAlp, maxAlp);
                        int newCop = Math.min(c + rwdCop, maxCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], curTime + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
