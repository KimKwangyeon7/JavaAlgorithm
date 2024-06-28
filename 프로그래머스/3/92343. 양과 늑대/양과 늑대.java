import java.util.*;

class Solution {
    static int res;
    static boolean[] visited;
    static List<Integer>[] list;
    static int N;
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        list = new ArrayList[N];
        for (int i = 0; i < N; i++){
            list[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < N-1; i++){
            list[edges[i][0]].add(edges[i][1]);
            list[edges[i][1]].add(edges[i][0]);
        }
        
        visited = new boolean[N];
        visited[0] = true;
        res = 0;
        int[] nodes = {0};
        dfs(0, 1, 0, info);
        
        return res;
    }
    
    private void dfs(int loc, int lamb, int wolf, int[] info){
        if (lamb <= wolf){
            return;
        } else {
            res = Math.max(res, lamb);
        }
        for(int k = 0; k < N; k++){
            if (!visited[k]){
                continue;
            }
            for (int i = 0; i < list[k].size(); i++){
                if (!visited[list[k].get(i)]){
                    int next = list[k].get(i);
                    visited[next] = true;

                    if (info[next] == 0){
                        dfs(next, lamb+1, wolf, info);
                    } else {
                        dfs(next, lamb, wolf+1, info);
                    }
                    visited[next] = false;
                }
            }
        }
    }
}