class Solution {
    static int[][] board;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        board = new int[rows][columns];
        int cnt = 1;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                board[i][j] = cnt++;
                //cnt++;
            }
        }
        int c = 0;
        for (int[] tmp: queries){
            int min = rotate(tmp[0]-1, tmp[1]-1, tmp[2]-1, tmp[3]-1);
            answer[c++] = min;
        }
        return answer;
    }
    private static int rotate(int sx, int sy, int ex, int ey){
        //int tmp = board[sx+1][sy];
        int prev = board[sx][sy];
        int min = board[sx][sy];
        // 위 오른쪽 방향
        for (int i = sy; i < ey; i++){
            int next = board[sx][i+1];
            min = Math.min(min, next);
            board[sx][i+1] = prev;
            prev = next;
        }
        // 오른쪽 아래 방향
        for (int i = sx; i < ex; i++){
            int next = board[i+1][ey];
            min = Math.min(min, next);
            board[i+1][ey] = prev;
            prev = next;
        }
        // 아래 왼쪽 방향
        for (int i = ey; i > sy; i--){
            int next = board[ex][i-1];
            min = Math.min(min, next);
            board[ex][i-1] = prev;
            prev = next;
        }
        // 왼쪽 위 방향
        for (int i = ex; i > sx; i--){
            int next = board[i-1][sy];
            min = Math.min(min, next);
            board[i-1][sy] = prev;
            prev = next;
        }
        return min;
    }
    
}