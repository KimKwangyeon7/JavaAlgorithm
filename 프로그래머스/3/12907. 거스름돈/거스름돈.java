import java.util.*;
class Solution {
    static int[] dp;
    public int solution(int n, int[] money) {
        int answer = 0;
        dp = new int[n+1];
        //Arrays.sort(money);
        
        for (int m: money){
            dp[m]++;
            if (m > n){
                continue;
            }
            for (int i = 1; i <= n; i++){
                if (i > m){
                    dp[i] += dp[i-m];
                    dp[i] %= 1000000007;
                }    
            }
        }
        
        return dp[n];
    }
}