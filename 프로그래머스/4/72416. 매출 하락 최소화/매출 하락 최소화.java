import java.util.*;
class Solution {
    static int[] val;
    static int N;
    static int[][] dp;
    static List<Integer>[] list;
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        val = sales.clone();
        N = sales.length;
        list = new ArrayList[N];
        for (int i = 0; i < N; i++){
            list[i] = new ArrayList<>();
        }
        for (int[] l: links){
            list[l[0]-1].add(l[1]-1);
        }
        dp = new int[N][2];
        dfs(0);
        
        return Math.min(dp[0][0], dp[0][1]);
    }
    
    private static void dfs(int cur){
        dp[cur][1] = val[cur];
        int extraCost = Integer.MAX_VALUE;
        
        if (list[cur].isEmpty()){
            return;
        }
        
        for (int next: list[cur]){
            dfs(next);

            if (dp[next][0] >= dp[next][1]){
                dp[cur][0] += dp[next][1];
                dp[cur][1] += dp[next][1];
                extraCost = 0;
            } else {
                dp[cur][0] += dp[next][0];
                dp[cur][1] += dp[next][0];
                extraCost = Math.min(extraCost, dp[next][1] - dp[next][0]);
            }
        }

        dp[cur][0] += extraCost;
    }
}