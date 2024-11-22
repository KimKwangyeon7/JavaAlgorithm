import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        List<Integer>[] win = new ArrayList[n+1];
        List<Integer>[] lose = new ArrayList[n+1];
        for (int i = 1; i <= n; i++){
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }
        for (int[] tmp: results){
            win[tmp[0]].add(tmp[1]);
            lose[tmp[1]].add(tmp[0]);
        }
        
        for (int j = 1; j <= n; j++){
            boolean[] visited = new boolean[n+1];
            Queue<Integer> qu = new ArrayDeque<>();
            qu.offer(j);
            visited[j] = true;
            int cnt = 1;
            while (!qu.isEmpty()){
                int tmp = qu.poll();
                for (int i = 0; i < win[tmp].size(); i++){
                    int next = win[tmp].get(i);
                    if (!visited[next]){
                        visited[next] = true;
                        cnt++;
                        qu.offer(next);
                    }   
                }   
            }
            qu.offer(j);
            visited[j] = true;
            while (!qu.isEmpty()){
                int tmp = qu.poll();
                for (int i = 0; i < lose[tmp].size(); i++){
                    int next = lose[tmp].get(i);
                    if (!visited[next]){
                        visited[next] = true;
                        cnt++;
                        qu.offer(next);
                    }   
                }   
            }
            if (cnt == n){
                answer++;
            }
        }
        return answer;
    }
}