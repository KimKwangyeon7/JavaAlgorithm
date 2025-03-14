class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int N = cookie.length;
        
        // 각 구간의 누적합을 저장하는 배열 (Prefix Sum)
        int[] prefixSum = new int[N + 1];
        
        // 누적합 계산
        for (int i = 0; i < N; i++) {
            prefixSum[i + 1] = prefixSum[i] + cookie[i];
        }

        // 중간 지점 m을 기준으로 모든 경우 탐색
        for (int m = 0; m < N - 1; m++) {
            int left = m, right = m + 1;
            int leftSum = cookie[m], rightSum = cookie[m + 1];

            while (left >= 0 && right < N) {
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum); // 최대값 업데이트
                }
                
                if (leftSum <= rightSum && left > 0) {
                    left--; // 왼쪽 확장
                    leftSum = prefixSum[m + 1] - prefixSum[left];
                } else if (rightSum <= leftSum && right < N - 1) {
                    right++; // 오른쪽 확장
                    rightSum = prefixSum[right + 1] - prefixSum[m + 1];
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}
