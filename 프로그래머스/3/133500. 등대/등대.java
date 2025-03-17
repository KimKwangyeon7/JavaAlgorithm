import java.util.*;
class Solution {
    static List<Integer>[] list;
    static int[][] dp;
    static boolean[] visited;
    public int solution(int n, int[][] lighthouse) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n+1];
        dp = new int[n+1][2];
        for (int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        for (int[] tmp: lighthouse){
            list[tmp[0]].add(tmp[1]);
            list[tmp[1]].add(tmp[0]);
        }
        visited = new boolean[n+1];
        dfs(1);
        
        answer = Math.min(dp[1][0], dp[1][1]);
        return answer;
    }
    
    private static void dfs(int cur){
        visited[cur] = true;
        dp[cur][1] = 1;
        dp[cur][0] = 0;
        
        for (int next: list[cur]){
            if (!visited[next]){
                dfs(next);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][1], dp[next][0]);
            }
            
        }
    }
}