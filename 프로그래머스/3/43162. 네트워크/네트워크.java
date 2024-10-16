class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n+1];
        for (int i = 1; i < n+1; i++){
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (computers[i][j] == 1){
                    union(Math.min(i+1, j+1), Math.max(i+1, j+1));
                }
            }
        }
        for (int i = 1; i < n+1; i++){
            if (parent[i] == i){
                answer++;
            }
        }
        return answer;
    }
    private static int find(int x){
        if (parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    private static void union(int x, int y){
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY){
            return;
        }
        parent[parentY] = parentX;
    }
}