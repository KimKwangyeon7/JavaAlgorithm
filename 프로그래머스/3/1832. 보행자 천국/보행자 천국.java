class Solution {
    static int MOD = 20170805;
    static int answer;
    static int M, N;
    public int solution(int m, int n, int[][] cityMap) {
        answer = 0;
        M = m;
        N = n;
        //dfs(0, 0, 0, 0, cityMap);
        
        int[][][] dp = new int[M][N][2];
        // 세로방향 첫번째 줄
        for (int i = 1; i < M; i++){
            if (cityMap[i][0] == 1){
                break;
            }
            dp[i][0][1] = 1;
        }
        // 가로방향 첫번째 줄
        for (int i = 1; i < N; i++){
            if (cityMap[0][i] == 1){
                break;
            }
            dp[0][i][0] = 1;
        }
        
        for (int i = 1; i < M; i++){
            for (int j = 1; j < N; j++){
                if (cityMap[i][j] != 1){
                    if (cityMap[i-1][j] != 2 && cityMap[i][j-1] != 2){
                        dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                        dp[i][j][1] = dp[i-1][j][0] + dp[i-1][j][1];
                    } else if (cityMap[i-1][j] != 2 && cityMap[i][j-1] == 2){
                        dp[i][j][0] = dp[i][j-1][0];
                        dp[i][j][1] = dp[i-1][j][0] + dp[i-1][j][1]; 
                    } else if (cityMap[i-1][j] == 2 && cityMap[i][j-1] != 2){
                        dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                        dp[i][j][1] = dp[i-1][j][1]; 
                    } else {
                        dp[i][j][0] = dp[i][j-1][0];
                        dp[i][j][1] = dp[i-1][j][1]; 
                    }
                    dp[i][j][0] %= MOD;
                    dp[i][j][1] %= MOD;
                }         
            }  
        }
        answer = (dp[M-1][N-1][0] + dp[M-1][N-1][1]) % MOD;
        //System.out.println(dp[1][2][0]+ dp[1][2][1]);
        return answer;
    }
    
    
    
    private static void dfs(int nowX, int nowY, int prevX, int prevY, int[][] board){
        if (nowX == M-1 && nowY == N-1){
            answer++;
            if (answer == MOD){
                answer = 0;
            }
            return;
        }
        if (board[nowX][nowY] == 0){
            // 아래로 가기
            if (isBoundary(nowX+1, nowY)){
                dfs(nowX+1, nowY, nowX, nowY, board);
            }
            // 오른쪽으로 가기
            if (isBoundary(nowX, nowY+1)){
                dfs(nowX, nowY+1, nowX, nowY, board);
            }
        } else if (board[nowX][nowY] == 2){
            // 왼쪽에서 온 경우
            if (prevX == nowX && isBoundary(nowX, nowY+1)){
                dfs(nowX, nowY+1, nowX, nowY, board);
            }
                
            // 위에서 온 경우
            if (prevY == nowY && isBoundary(nowX+1, nowY)){
                dfs(nowX+1, nowY, nowX, nowY, board);
            }
        } 
    }
    private static boolean isBoundary(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    } 
}