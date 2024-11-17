class Solution {
    static int answer;
    //static int[] res;
    public int solution(int n) {
        answer = 0;
        boolean[][] visited = new boolean[n][n];
        //res = new int[n];
        dfs(0, n, visited);
        return answer;
    }
    private static void dfs(int L, int N, boolean[][] visited){
        if (L == N){
            answer++;
            // for (int i = 0; i < N; i++){
            //     System.out.print(res[i] + " ");
            // }
            // System.out.println();
            return;
        }
        boolean[][] tmp = copyArray(visited, N);
        for (int i = 0; i < N; i++){
            if (!visited[L][i]){
                visited = checkCross(visited, L, i);
                visited = checkStraight(visited, L, i);
                //res[L] = i;
                dfs(L+1, N, visited);
                for (int j = 0; j < N; j++){
                    visited[j] = tmp[j].clone();
                }
            }
        }
    }
    private static boolean[][] copyArray(boolean[][] visited, int N){
        boolean[][] tmp = new boolean[N][N];
        for (int i = 0; i < N; i++){
            tmp[i] = visited[i].clone();
        }
        return tmp;
    }
    private static boolean[][] checkCross(boolean[][] visited, int x, int y){
        int row = visited.length;
        int col = visited[0].length;
        int tmpX = x;
        int tmpY = y;
        while (x < row && y < col){
            visited[x][y] = true;
            x++;
            y++;
        }
        x = tmpX;
        y = tmpY;
        while (x >= 0 && y < col){
            visited[x][y] = true;
            x--;
            y++;
        }
        x = tmpX;
        y = tmpY;
        while (x < row && y >= 0){
            visited[x][y] = true;
            x++;
            y--;
        }
        x = tmpX;
        y = tmpY;
        while (x >= 0 && y >= 0){
            visited[x][y] = true;
            x--;
            y--;
        }
        return visited;
    }
    private static boolean[][] checkStraight(boolean[][] visited, int x, int y){
        int row = visited.length;
        int col = visited[0].length;
        
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (i == x || j == y){
                    visited[i][j] = true;
                }
            }
        }
        return visited;
    }
}