class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int INT = 1000000007;
        int answer = 0;
        
        int[][] board = new int[n][m];
        int[][] dis = new int[n][m];
        for (int[] p: puddles){
            board[p[1]-1][p[0]-1] = 1;
        }
        
        for (int i = 0; i < n; i++){
            if (board[i][0] == 0){
                dis[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < m; i++){
            if (board[0][i] == 0){
                dis[0][i] = 1;
            } else {
                break;
            }
        }
        
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                if (board[i][j] == 0){
                    dis[i][j] = dis[i-1][j] + dis[i][j-1];
                    dis[i][j] = dis[i][j] % INT;
                } 
            }
        }
  
        answer = dis[n-1][m-1] % INT;
        
        return answer;
    }
}