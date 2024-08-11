class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        if (len == 1) {
        	return sticker[0];
        } else if (len == 2) {
        	return Math.max(sticker[0], sticker[1]);
        }
        int[] dp = new int[len];
        
        // 첫번째 스티커 사용
        dp[0] = sticker[0];
        dp[1] = sticker[0];
        for (int i = 2; i < len; i++) {
        	if (i == len-1) {
        		dp[i] = dp[i-1];
        	} else {
        		dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        	}
        }
        answer = dp[len-1];
        dp = new int[len];
        // 두번째 스티커 사용
        dp[1] = sticker[1];
        for (int i = 2; i < len; i++) {
        	dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        answer = Math.max(answer, dp[len-1]);
        
        return answer;
    }

}