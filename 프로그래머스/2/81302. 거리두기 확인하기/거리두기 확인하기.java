import java.util.*;
class Solution {
    static char[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        for (int i = 0; i < 5; i++){
            board = new char[5][5];
            for (int j = 0; j < 5; j++){
                board[j] = places[i][j].toCharArray();  
            }
            boolean flag = false;
            for (int p = 0; p < 5; p++){
                for (int q = 0; q < 5; q++){
                    if (board[p][q] == 'P'){
                        if (!check(p, q)){
                            answer[i] = 0;
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag){
                    break;
                }
            }
        }
        return answer;
    }
    private static boolean check(int x, int y){
        Queue<int[]> qu = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;
        qu.add(new int[] {x, y, 0});
        while (!qu.isEmpty()){
            int[] tmp = qu.poll();
            for (int k = 0; k < 4; k++){
                int xx = dx[k] + tmp[0];
                int yy = dy[k] + tmp[1];
                
                if (!isBoundary(xx, yy) || visited[xx][yy] || board[xx][yy] == 'X' || tmp[2]+1 > 2){ // 범위에서 벗어나거나 이미 방문했거나 벽이면 => 패스
                continue;
                }
                if (board[xx][yy] == 'P'){ // 응시자인 경우
                    //System.out.println(xx +" "+ yy + " ");
                    //System.out.println(tmp[2]+1);
                    return false;
                } else {
                    qu.add(new int[] {xx, yy, tmp[2]+1});
                    visited[xx][yy] = true;
                }
            }
            
        }
        return true;
    }
    private static boolean isBoundary(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
}