class Solution {
    public int solution(int n, int[] tops) {
        final int mod = 10007;
        int[][] dp = new int[n][2];
        if (tops[0] == 1){
            dp[0][0] = 3;
        } else {
            dp[0][0] = 2;
        }
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (tops[i] == 0){
                dp[i][0] = (dp[i-1][0] * 2 + dp[i-1][1]) % mod;
                dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % mod;
            } else {
                dp[i][0] = (dp[i-1][0] * 3 + dp[i-1][1] * 2) % mod;
                dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % mod;
            }
        }
        return (dp[n-1][0] + dp[n-1][1]) % mod;
    }
}