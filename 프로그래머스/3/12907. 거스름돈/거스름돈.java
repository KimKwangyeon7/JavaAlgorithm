import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        
        for (int coin: money){
            for (int i = 1; i <= n-coin; i++){
                if (dp[i] != 0){
                    dp[i+coin] += dp[i];
                } 
            }
            for (int i = 1; i <= n/coin; i++){
                dp[coin*i]++;
            }
        }
        return dp[n];
    }
}