import java.util.*;
class Solution {
    static int[] sorted;
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = Integer.MAX_VALUE;
        int mid = 0;
        
        while (left <= right){
            mid = (left + right) / 2;
            if (check(stones, mid, k)){
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    private static boolean check(int[] stones, int mid, int k){
        int cnt = 0;
        for (int stone: stones){
            if (stone >= mid){
                cnt = 0;
            } else {
                cnt++;
                if (cnt == k){
                    return false;
                }
            }
        }
        return true;
    }
}