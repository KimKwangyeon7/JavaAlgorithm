class Solution {
    static boolean[] rightCross;
    static boolean[] leftCross;
    static boolean[] columns;
    static int answer;
    public int solution(int n) {
        answer = 0;
        columns = new boolean[n];
        leftCross = new boolean[2*n];
        rightCross = new boolean[2*n];
        
        dfs(0, n);
        
        return answer;
    }
    private static void dfs(int row, int n){
        if(row == n){
            answer++;
            return;
        }
        
        for (int i = 0; i < n; i++){
            if (columns[i] || leftCross[row-i+n] || rightCross[row+i]){
                continue;
            }
            columns[i] = true;
            leftCross[row-i+n] = true;
            rightCross[row+i] = true; 
            dfs(row+1, n);
            columns[i] = false;
            leftCross[row-i+n] = false;
            rightCross[row+i] = false; 
        }
        
    }
}