class Solution {
    static char[][] arr;
    //static char[][] copy;
    static boolean[][] visited;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        arr = new char[m][n];
        //copy = new char[m][n];
        for (int i = 0; i < m; i++){
            arr[i] = board[i].toCharArray();
            //copy[i] = board[i].toCharArray();
        }
        while (true){
            int cnt = 0;
            visited = new boolean[m][n];
            for (int i = 0; i < m-1; i++){
                for (int j = 0; j < n-1; j++){
                    cnt += checkSquare(i, j);
                }
            }
            answer += cnt;
            if (cnt == 0){
                break;
            }
            deleteBlocks(m, n);
        }
        
        return answer;
    }
    private static int checkSquare(int x, int y){
        if (arr[x][y] == ' '){
            return 0;
        }
        char c = arr[x][y];
        int cnt = 0;
        if (arr[x][y+1] == c && arr[x+1][y] == c && arr[x+1][y+1] == c){
            if (!visited[x][y]){
                visited[x][y] = true;
                cnt++;
            }
            if (!visited[x+1][y]){
                visited[x+1][y] = true;
                cnt++;
            }
            if (!visited[x][y+1]){
                visited[x][y+1] = true;
                cnt++;
            }
            if (!visited[x+1][y+1]){
                visited[x+1][y+1] = true;
                cnt++;
            }
        }
        return cnt;
    }
    private static void deleteBlocks(int m, int n){
        for (int j = 0; j < n; j++){
            for (int i = 0; i < m; i++){
                if (visited[i][j]){
                    for (int k = i; k > 0; k--){
                        arr[k][j] = arr[k-1][j];
                    }
                    arr[0][j] = ' ';
                }
            }
        }
    }
}