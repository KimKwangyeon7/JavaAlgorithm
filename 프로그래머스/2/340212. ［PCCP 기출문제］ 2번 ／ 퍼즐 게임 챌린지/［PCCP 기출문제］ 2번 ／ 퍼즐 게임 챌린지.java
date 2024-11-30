import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) { 
        int left = 1;
        int right = 100000;
        int answer = Integer.MAX_VALUE;
        while (left <= right){
            int mid = (left+right) / 2;
            if (check(mid, diffs, times, limit)){
                answer = Math.min(answer, mid);
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return answer;
    }
    private static boolean check(int level, int[] diffs, int[] times, long limit){
        int cnt = 0;
        long sum = 0;
        int time_prev = 0;
        for (int diff: diffs){
            if (diff <= level){
                sum += times[cnt];
            } else {
                sum += (diff-level) * (times[cnt] + time_prev) + times[cnt];
            }
            time_prev = times[cnt++];
            if (sum > limit){
                return false;
            }
        }
        return true;
    }
}