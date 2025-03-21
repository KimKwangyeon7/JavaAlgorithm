class Solution {
    public int solution(int[] cookie) {
        int N = cookie.length;
        int answer = 0;
        for (int k = 0; k < N-1; k++){
            int left = k;
            int right = k+1;
            
            int leftSum = cookie[k];
            int rightSum = cookie[k+1];
            
            while (left >= 0 && right < N){
                if (leftSum == rightSum){
                    answer = Math.max(answer, leftSum);
                }
                
                if (leftSum >= rightSum && right < N-1){
                    right++;
                    rightSum += cookie[right];
                } else if (leftSum <= rightSum && left > 0){
                    left--;
                    leftSum += cookie[left];
                } else {
                    break;
                }
            }
            
        }
        return answer;
    }
}