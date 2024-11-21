import java.util.*;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int[][] board = new int[101][101];
        Queue<int[]> qu = new ArrayDeque<>();
        int[] order = new int[routes.length];
        Arrays.fill(order, 1);
        int[][] visited = new int[101][101];
        int time = 0;
        for (int i = 0; i < routes.length; i++){
            qu.offer(new int[] {i+1, points[routes[i][0]-1][0], points[routes[i][0]-1][1], 0});
        }
        
        while (!qu.isEmpty()){
            int[] tmp = qu.poll();
            if (time != tmp[3]){
                visited = new int[101][101];
                time++;
            }
            int idx = tmp[0];
            int sx = tmp[1];
            int sy = tmp[2];
            visited[sx][sy]++;
            if (visited[sx][sy] == 2){
                answer++;
            }
            if (order[idx-1] == routes[0].length){
                continue;
            }
            int ex = points[routes[idx-1][order[idx-1]]-1][0];
            int ey = points[routes[idx-1][order[idx-1]]-1][1];
            int xx = sx;
            int yy = sy;
            // 이동 방향 정하기
            if (sx == ex && sy != ey){
                if (sy > ey){
                    yy = sy-1;
                } else {
                    yy = sy+1;
                }
            } else if (sy == ey && sx != ex){
                if (sx > ex){
                    xx = sx-1;
                } else {
                    xx = sx+1;
                }
            } else if (sx != ex && sy != ey){
                if (sx > ex){
                    xx = sx-1;
                } else {
                    xx = sx+1;
                }
            }
            if (xx == ex && yy == ey){ // 목적지에 도착
                order[idx-1]++;
            }
            qu.offer(new int[] {idx, xx, yy, tmp[3]+1});
        }
        
        return answer;
    }
}