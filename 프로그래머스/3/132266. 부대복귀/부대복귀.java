import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer>[] list = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++){
            list[i] = new ArrayList<>();
        }
        for (int[] r: roads){
            list[r[0]].add(r[1]);
            list[r[1]].add(r[0]);
        }
        pq.offer(destination);
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;
        while (!pq.isEmpty()){
            int tmp = pq.poll();
            for (int i = 0; i < list[tmp].size(); i++){
                int next = list[tmp].get(i);
                if (dist[tmp] + 1 < dist[next]){
                    dist[next] = dist[tmp] + 1;
                    pq.offer(next);
                }
            }
        }
        
        int cnt = 0;
        for (int s: sources){
            if (dist[s] == Integer.MAX_VALUE){
                answer[cnt++] = -1;
            } else {
                answer[cnt++] = dist[s];
            } 
        }
        
        return answer;
    }
}