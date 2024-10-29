import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        int in;
        int out;
        
        public Dot(int in, int out){
            this.in = in;
            this.out = out;
        }
        
        public int compareTo(Dot o){
            return Integer.compare(this.out, o.out);
        }
    }
        
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        for (int[] r: routes){
            pq.offer(new Dot(r[0], r[1]));
        }
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()){
            Dot tmp = pq.poll();
            if (min >= tmp.in && min <= tmp.out){
                continue;
            }
            min = tmp.out;
            answer++;
        }
        return answer;
    }
}