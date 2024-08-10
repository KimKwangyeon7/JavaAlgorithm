import java.util.*;
class Solution {
    
    static List<List<Dot>> board;
    static boolean[] visit;
    
    static class Dot implements Comparable<Dot>{
        int idx;
        int cost;
        
        public Dot(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        
        public int compareTo(Dot o) {
        	return Integer.compare(cost, o.cost);
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        board = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            board.add(new ArrayList());
        }
        int len = fares.length;
        for(int i = 0; i < len; i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int z = fares[i][2];
            board.get(x).add(new Dot(y, z));
            board.get(y).add(new Dot(x, z));
        }
        
        int[] together = dijkstra(s, n); //함께 타는 거 계산
        int[] aloneA = dijkstra(a, n); //a 혼자
        int[] aloneB = dijkstra(b, n); //b 혼자
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i<=n; i++) {
            int tmp = together[i] + aloneA[i] + aloneB[i];
            if(answer>tmp) answer = tmp;
        }
        
        return answer;
    }
    
    private static int[] dijkstra(int start, int N) {
        
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        pq.offer(new Dot(start, 0));
        
        while(!pq.isEmpty()) {
            Dot tmp = pq.poll();
            
            if(dist[tmp.idx] < tmp.cost) {
            	continue;
            }
            
            for(Dot next: board.get(tmp.idx)) {
                if(dist[next.idx] > tmp.cost + next.cost) {
                    dist[next.idx] = tmp.cost + next.cost;
                    pq.offer(new Dot(next.idx, dist[next.idx]));
                }
            }
            
        }
        
        //System.out.println(Arrays.toString(dist));
        return dist;
    }
}