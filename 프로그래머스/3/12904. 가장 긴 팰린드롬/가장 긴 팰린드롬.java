class Solution {
    public int solution(String s){
        int N = s.length();
        
        boolean[][] dp = new boolean[N][N];
        int max = 1;
        for (int i = 0; i < N; i++){
            dp[i][i] = true;
        }

        for (int i = 0; i < N-1; i++){
            if (s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                max = 2;
            }
        }

        for (int k = 3; k <= N; k++){
            for (int i = 0; i <= N-k; i++){
                if (dp[i+1][i+k-2] && s.charAt(i) == s.charAt(i+k-1)){
                    dp[i][i+k-1] = true;
                    max = k;
                }
            }
        }
        return max;
    }
}