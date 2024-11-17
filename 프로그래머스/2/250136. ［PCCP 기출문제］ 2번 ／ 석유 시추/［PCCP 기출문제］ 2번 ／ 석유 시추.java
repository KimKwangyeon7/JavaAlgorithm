import java.util.*;
class Solution {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer, Integer> map;
    public int solution(int[][] land) {
        int cnt = 3;
        int answer = 0;
        map = new HashMap<>();
        for (int i = 0; i < land.length; i++){
            for (int j = 0; j < land[i].length; j++){
                if (land[i][j] == 1){
                    Queue<int[]> qu = new ArrayDeque<>();
                    qu.offer(new int[] {i, j});
                    int ans = 1;
                    land[i][j] = cnt;
                    while (!qu.isEmpty()){
                        int[] tmp = qu.poll();

                        for (int k = 0; k < 4; k++){
                            int x = tmp[0] + dx[k];
                            int y = tmp[1] + dy[k];

                            if (!isBoundary(x, y, land.length, land[0].length) || land[x][y] != 1){
                                continue;
                            }
                            land[x][y] = cnt;
                            qu.offer(new int[] {x, y});
                            ans++;
                        }
                    }
                    map.put(cnt, ans);
                    cnt++;
                }
            }
        }
        
        for (int i = 0; i < land[0].length; i++){
            answer = Math.max(answer, check(i, land));
        }
        return answer;
    }
    private static int check(int col, int[][] land){
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < land.length; i++){
            if (land[i][col] != 0 && !set.contains(land[i][col])){
                sum += map.get(land[i][col]);
                set.add(land[i][col]);
            }
        }
        return sum;
    }
    private static boolean isBoundary(int x, int y, int N, int M){
        return x >= 0 && x< N && y >= 0 && y < M;
    }
}