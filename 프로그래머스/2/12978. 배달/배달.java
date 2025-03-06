import java.util.*;

class Solution {
    static class Dot implements Comparable<Dot>{
        int to;
        int time;
        public Dot(int to, int time){
            this.to = to;
            this.time = time;
        }
        public int compareTo(Dot o){
            return Integer.compare(this.time, o.time);
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        List<Dot>[] towns = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++){
            towns[i] = new ArrayList<>();
        }
        for (int i = 0; i < road.length; i++){
            int[] tmp = road[i];
            towns[tmp[0]].add(new Dot(tmp[1], tmp[2]));
            towns[tmp[1]].add(new Dot(tmp[0], tmp[2]));
        }
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Dot(1, 0));
        dist[1] = 0;
        while (!pq.isEmpty()){
            Dot tmp = pq.poll();
            
            if (dist[tmp.to] > K){
                continue;
            }
            
            for (int i = 0; i < towns[tmp.to].size(); i++){
                Dot next = towns[tmp.to].get(i);
                if (dist[next.to] > dist[tmp.to] + next.time){
                    dist[next.to] = dist[tmp.to] + next.time;
                    pq.offer(new Dot(next.to, dist[next.to]));
                }
            }
        }
        for (int i = 1; i < N+1; i++){
            if (dist[i] <= K){
                answer++;
            }
        }

        return answer;
    }
}