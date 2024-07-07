import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = 0L;
        long sum2 = 0L;
        int N = queue1.length;
        Queue<Integer> qu1 = new ArrayDeque<>();
        Queue<Integer> qu2 = new ArrayDeque<>();
        for (int i = 0; i < N; i++){
            sum1 += queue1[i];
            qu1.offer(queue1[i]);
        } 
        
        for (int i = 0; i < N; i++){
            sum2 += queue2[i];
            qu2.offer(queue2[i]);
        }
        
        if (((sum1+sum2) % 2) == 1){
            return -1;
        } else {
            int cnt = 0;
            while (cnt <= N*4){
                if (sum1 < sum2){
                    int tmp = qu2.poll();
                    //System.out.println(tmp);
                    qu1.offer(tmp);
                    cnt++;
                    sum1 += tmp;
                    sum2 -= tmp;
                } else if (sum1 > sum2){
                    int tmp = qu1.poll();
                    qu2.offer(tmp);
                    cnt++;
                    sum1 -= tmp;
                    sum2 += tmp;
                } else {
                    return cnt;
                } 
            }
            return -1;
        }
    }
}