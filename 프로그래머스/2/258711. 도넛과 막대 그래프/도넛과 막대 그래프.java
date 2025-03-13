import java.util.*;
class Solution {
    static List<Integer>[] list;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int max = 0;
        for (int[] e: edges){
            max = Math.max(Math.max(max, e[0]), e[1]);
        }
        int[] from = new int[max+1];
        int[] to = new int[max+1];
        list = new ArrayList[max+1];
        for (int i = 1; i <= max; i++){
            list[i] = new ArrayList<>();
        }
        for (int[] e: edges){
            from[e[0]]++;
            to[e[1]]++;
            list[e[0]].add(e[1]);
        }

        for (int i = 1; i <= max; i++){
            if (to[i] == 0 && from[i] >= 2){
                answer[0] = i;
                break;
            }
        }
        
        for (int next: list[answer[0]]){
            int idx = check(next, max);
            answer[idx]++;
        }    
        return answer;
    }
    private static int check(int start, int N){
        Queue<int[]> qu = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        qu.offer(new int[] {start, 0});
        visited[start] = true;
        int cnt = 1;
        while (!qu.isEmpty()){
            int[] cur = qu.poll();
            
            if (cur[0] == start && cur[1] > 0){ // 도넛 또는 8자
                if (cnt == cur[1]){
                    return 1;
                } else if (cnt+1 == cur[1]){
                    return 3;
                }
            }
            
            for (int next: list[cur[0]]){
                if (!visited[next]){
                    cnt++;
                    visited[next] = true;
                }
                qu.offer(new int[] {next, cur[1]+1});
            }
        }
        // 막대 모양
        return 2;
    }
    
}