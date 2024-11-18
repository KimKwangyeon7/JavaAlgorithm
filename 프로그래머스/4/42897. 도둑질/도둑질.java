class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int[] dp = new int[1000001];
        int len = money.length;
        // 첫번째 집을 훔치는 경우
        dp[1] = money[0];
        dp[2] = money[0];
        for (int i = 3; i < len; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i-1]);
        }
        answer = Math.max(dp[len-1], answer);
        
        // 첫번째 집을 훔치지 않는 경우
        dp[1] = 0;
        dp[2] = money[1];
        for (int i = 3; i <= len; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i-1]);
        }
        answer = Math.max(dp[len], answer);
        return answer;
    }
}