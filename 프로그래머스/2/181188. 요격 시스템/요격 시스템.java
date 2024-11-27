import java.util.*;
class Solution {
    static class Dot implements Comparable<Dot>{
        int row;
        int col;
        public Dot(int row, int col){
            this.row = row;
            this.col = col;
        }
        public int compareTo(Dot o){
            return Integer.compare(row, o.row);
        }
    }
    public int solution(int[][] targets) {
        int answer = 1;
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        for (int[] tmp: targets){
            pq.offer(new Dot(tmp[0], tmp[1]));
        }
        int prev = Integer.MAX_VALUE;
        while (!pq.isEmpty()){
            Dot tmp = pq.poll();
            if (tmp.row >= prev){
                prev = tmp.col;
                answer++;
            } else {
                if (tmp.col < prev){
                    prev = tmp.col;
                } 
            }
        }
        return answer;
    }
}