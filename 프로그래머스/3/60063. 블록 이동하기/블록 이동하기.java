import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;
        boolean[][][] visited = new boolean[N][N][2];
        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[] {0, 0, 0, 1, 0});
        visited[0][0][0] = true;
        
        while (!qu.isEmpty()){
            int[] tmp = qu.poll();
            int x1 = tmp[0];
            int y1 = tmp[1];
            int x2 = tmp[2]; 
            int y2 = tmp[3];
            int cnt = tmp[4];
            
            if ((x1 == N-1 && y1 == N-1) || (x2 == N-1 && y2 == N-1)){
                return cnt;
            }
            
            for (int k = 0; k < 4; k++){ // 그대로 상하좌우 이동
                int nx1 = x1 + dx[k];
                int ny1 = y1 + dy[k];
                int nx2 = x2 + dx[k];
                int ny2 = y2 + dy[k];
                
                if (!isBoundary(nx1, ny1, N) || !isBoundary(nx2, ny2, N) || board[nx1][ny1] == 1 || board[nx2][ny2] == 1){
                    continue;
                }
                if (nx1 == nx2){ // 가로 방향
                    if (!visited[nx1][Math.min(ny1, ny2)][0]){
                        visited[nx1][Math.min(ny1, ny2)][0] = true;
                        qu.offer(new int[] {nx1, Math.min(ny1, ny2), nx2, Math.max(ny1, ny2), cnt+1});
                    }
                } else { // 세로 방향
                    if (!visited[Math.min(nx1, nx2)][ny1][1]){
                        visited[Math.min(nx1, nx2)][ny1][1] = true;
                        qu.offer(new int[] {Math.min(nx1, nx2), ny1, Math.max(nx1, nx2), ny2, cnt+1});
                    }
                }
            }
            // 회전
            if (x1 == x2){ // 가로 모양일 때
                int leftY = Math.min(y1, y2);
                int rightY = Math.max(y1, y2);
                
                // 왼쪽 좌표를 축
                // 위로
                if (isBoundary(x1-1, rightY, N) && isBoundary(x1-1, leftY, N) &&
                    board[x1-1][rightY] == 0 && board[x1-1][leftY] == 0 && !visited[x1-1][leftY][1]){
                    visited[x1-1][leftY][1] = true;
                    qu.offer(new int[] {x1-1, leftY, x1, leftY, cnt+1});
                }
                // 아래로
                if (isBoundary(x1+1, rightY, N) && isBoundary(x1+1, leftY, N) &&
                    board[x1+1][rightY] == 0 && board[x1+1][leftY] == 0 && !visited[x1][leftY][1]){
                    visited[x1][leftY][1] = true;
                    qu.offer(new int[] {x1, leftY, x1+1, leftY, cnt+1});
                }
                
                // 오른쪽 좌표를 축
                // 위로
                if (isBoundary(x1-1, leftY, N) && isBoundary(x1-1, rightY, N) &&
                    board[x1-1][leftY] == 0 && board[x1-1][rightY] == 0 && !visited[x1-1][rightY][1]){
                    visited[x1-1][rightY][1] = true;
                    qu.offer(new int[] {x1-1, rightY, x1, rightY, cnt+1});
                }
                // 아래로
                if (isBoundary(x1+1, leftY, N) && isBoundary(x1+1, rightY, N) &&
                    board[x1+1][leftY] == 0 && board[x1+1][rightY] == 0 && !visited[x1][rightY][1]){
                    visited[x1][rightY][1] = true;
                    qu.offer(new int[] {x1, rightY, x1+1, rightY, cnt+1});
                }
            } else { // 세로 모양일 때
                int highX = Math.min(x1, x2);
                int lowX = Math.max(x1, x2);
                // 위 좌표를 축
                // 왼쪽
                if (isBoundary(highX+1, y1-1, N) && isBoundary(highX, y1-1, N) &&
                    board[highX+1][y1-1] == 0 && board[highX][y1-1] == 0 && !visited[highX][y1-1][0]){
                    visited[highX][y1-1][0] = true;
                    qu.offer(new int[] {highX, y1-1, highX, y1, cnt+1});
                }
                // 오른쪽
                if (isBoundary(highX+1, y1+1, N) && isBoundary(highX, y1+1, N) &&
                    board[highX+1][y1+1] == 0 && board[highX][y1+1] == 0 && !visited[highX][y1][0]){
                    visited[highX][y1][0] = true;
                    qu.offer(new int[] {highX, y1, highX, y1+1, cnt+1});
                }
                // 아래 좌표를 축
                // 왼쪽
                if (isBoundary(lowX-1, y1-1, N) && isBoundary(lowX, y1-1, N) &&
                    board[lowX-1][y1-1] == 0 && board[lowX][y1-1] == 0 && !visited[lowX][y1-1][0]){
                    visited[lowX][y1-1][0] = true;
                    qu.offer(new int[] {lowX, y1-1, lowX, y1, cnt+1});
                }
                // 오른쪽
                if (isBoundary(lowX-1, y1+1, N) && isBoundary(lowX, y1+1, N) &&
                    board[lowX-1][y1+1] == 0 && board[lowX][y1+1] == 0 && !visited[lowX][y1][0]){
                    visited[lowX][y1][0] = true;
                    qu.offer(new int[] {lowX, y1, lowX, y1+1, cnt+1});
                }
            }
            
            
        }
        return answer;
    }
    private boolean isBoundary(int x, int y, int N){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}