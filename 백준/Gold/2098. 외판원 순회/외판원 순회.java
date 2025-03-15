import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 1_000_000_000; // 충분히 큰 값 (최대 비용보다 크도록 설정)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = new int[N][N];
        dp = new int[N][(1 << N)]; // dp[i][visited] -> i에서 visited 상태로 가는 최소 비용

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1); // DP 배열 초기화
            for (int j = 0; j < N; j++) {
                W[i][j] = sc.nextInt();
            }
        }
        sc.close();

        System.out.println(tsp(0, 1 << 0)); // 0번 도시에서 시작
    }

    static int tsp(int current, int visited) {
        // 모든 도시 방문 완료 (출발지로 복귀)
        if (visited == (1 << N) - 1) {
            return (W[current][0] > 0) ? W[current][0] : INF;
        }

        // 이미 계산된 경우 반환
        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }

        int minCost = INF;

        for (int next = 0; next < N; next++) {
            // 방문하지 않은 도시 & 갈 수 있는 길이 있는 경우
            if ((visited & (1 << next)) == 0 && W[current][next] > 0) {
                int cost = W[current][next] + tsp(next, visited | (1 << next));
                minCost = Math.min(minCost, cost);
            }
        }

        return dp[current][visited] = minCost;
    }
}
