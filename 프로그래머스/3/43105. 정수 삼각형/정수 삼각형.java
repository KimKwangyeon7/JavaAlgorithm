class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int len = triangle.length;
        int[][] dp = new int[len][];
        dp[0] = new int[1];
        dp[0][0] = triangle[0][0];
        answer = dp[0][0];
        for (int i = 1; i < len; i++){
            dp[i] = new int[i+1];
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][triangle[i].length-1] = dp[i-1][triangle[i-1].length-1] + triangle[i][triangle[i].length-1];
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][triangle[i].length-1]));
        }
        
        for (int i = 2; i < len; i++){
            for (int j = 1; j < triangle[i].length-1; j++){
                dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
                answer = Math.max(answer, dp[i][j]);
            }
        }  
        
        return answer;
    }
}