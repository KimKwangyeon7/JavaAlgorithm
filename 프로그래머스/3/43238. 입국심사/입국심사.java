import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        long cnt;
        long start;
        
        public Dot(long cnt, long start){
            this.cnt = cnt;
            this.start = start;
        }
        public int compareTo(Dot o){
            return Long.compare(this.cnt*this.start, o.cnt*o.start);
        }
    }
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        long right = (long)Math.pow(10, 18);
        long mid = (left + right) / 2;
        while (left <= right){
            mid = (left + right) / 2;
            if (check(mid, times) < n){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        //System.out.println(left + " " + right);
        return left;
        
        
        
//         PriorityQueue<Dot> pq = new PriorityQueue<>();
//         for (int t: times){
//             pq.offer(new Dot(1, (long)t));
//         }
        
//         for (int i = 0; i < n; i++){
//             Dot tmp = pq.poll();
//             pq.offer(new Dot(tmp.cnt+1, tmp.start));
//             answer = tmp.cnt * tmp.start;
//         }
//        return answer;
    }
    private static long check(long time, int[] times){
        int len = times.length;
        long total = 0;
        for (int i = 0; i < len; i++){
            total += time / (long)times[i];
        }
        return total;
    }
}