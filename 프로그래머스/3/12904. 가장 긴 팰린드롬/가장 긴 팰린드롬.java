class Solution {
    public int solution(String s) {
        int len = s.length();
        int maxLength = 1; // 최소 회문 길이는 1입니다.

        for (int i = 0; i < len; i++) {
            // 홀수 길이 회문 검사
            maxLength = Math.max(maxLength, expandAroundCenter(s, i, i));

            // 짝수 길이 회문 검사
            maxLength = Math.max(maxLength, expandAroundCenter(s, i, i + 1));
        }
        
        return maxLength;
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 현재 회문의 길이 반환
    }
}