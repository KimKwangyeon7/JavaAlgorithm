import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] list = new ArrayList[n+1];
        for (int i = 1; i < n+1; i++){
            list[i] = new ArrayList<>();
        }
        
        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] e: edge){
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        pq.offer(1);
        int max = 0;
        int cnt = 0;
        while (!pq.isEmpty()){
            int tmp = pq.poll();
            
            for (int i = 0; i < list[tmp].size(); i++){
                int next = list[tmp].get(i);
                if (dis[tmp] + 1 < dis[next]){
                    dis[next] = dis[tmp] + 1;
                    pq.offer(next);
                } 
            }
        }
        
        for (int i = 1; i < n+1; i++){
            //System.out.println(dis[i]);
            if (dis[i] > max){
                max=  dis[i];
                cnt = 1;
            } else if (dis[i] == max){
                cnt++;
            }
        }
        
        return cnt;
    }
}