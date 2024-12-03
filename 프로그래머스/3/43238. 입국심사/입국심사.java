import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = -1;
        long left = 0;
        long right = (long)Math.pow(10, 18);
        long mid = (left + right) / 2;
        
        while (left <= right){
            mid = (left + right) / 2;
            
            if (test(mid, times) < n){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    private static long test(long time, int[] times){
        int len = times.length;
        long total = 0;
        
        for (int i = 0; i < len; i++){
            total += time / (long)times[i];
        }
        return total;
    }
}