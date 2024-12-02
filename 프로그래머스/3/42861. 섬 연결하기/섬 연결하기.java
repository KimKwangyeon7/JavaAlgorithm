import java.util.*;
class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;

        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < costs.length; i++){
            pq.offer(costs[i]);
        }
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
        int cnt = 0;

        while (!pq.isEmpty()){
            int[] tmp = pq.poll();
            if (find(parent[tmp[0]]) == find(parent[tmp[1]])){
                continue;
            }
            union(tmp[0], tmp[1]);
            answer += tmp[2];
            cnt++;
            if (cnt == n-1){
                return answer;
            }
        }
        
        return answer;
    }
    private static int find(int x){
        if (x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    private static void union(int x, int y){
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX == parentY){
            return;
        }
        if (x < y){
            parent[parentY] = parentX;
        } else {
            parent[parentX] = parentY;
        }
    }
}