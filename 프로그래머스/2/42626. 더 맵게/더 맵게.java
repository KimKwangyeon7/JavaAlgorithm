import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s: scoville){
            pq.offer(s);
        }
        int cnt = 0;
        while (!pq.isEmpty()){
            int fir = pq.poll();
            if (pq.isEmpty()){
                if (fir >= K){
                    return cnt;
                } else {
                    return -1;
                }
            }
            int sec = pq.poll();
            if (fir >= K){
                return cnt;
            }
            int mix = fir + sec * 2;
            cnt++;
            pq.offer(mix);
        }
        return answer;
    }
}