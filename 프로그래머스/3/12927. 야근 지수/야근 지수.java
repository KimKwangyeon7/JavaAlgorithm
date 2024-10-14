import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        for (int w: works){
            qu.offer(w);
        }
        int len = works.length;
        for (int i = 0; i <  n; i++){
            if (qu.isEmpty()){
                break;
            }
            int tmp = qu.poll();
            if (tmp-1 == 0){
                continue;
            } 
            qu.offer(tmp-1);
        }
        
        while (!qu.isEmpty()){
            int tmp = qu.poll();
            answer += Math.pow(tmp, 2);
        }
        return answer;

    }
}