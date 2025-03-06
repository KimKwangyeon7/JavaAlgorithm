import java.util.*;
class Solution {
    static int[] sale = {10, 20, 30, 40};
    static List<int[]> cand;
    public int[] solution(int[][] users, int[] emoticons) {
        
        int[] res = new int[emoticons.length];
        cand = new ArrayList<>();
        dfs(0, res, users, emoticons, emoticons.length);
        Collections.sort(cand, (a, b) -> {
            if (a[0] == b[0]){
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(b[0], a[0]);
        });
        return cand.get(0);
    }
    private static void dfs(int L, int[] res, int[][] users, int[] emoticons, int N){
        if (L == N){
            check(res, users, emoticons);
            return;
        }   
        for (int k = 0; k < 4; k++){
            res[L] = sale[k];
            dfs(L+1, res, users, emoticons, N);
        }
    }
    private static void check(int[] res, int[][] users, int[] emoticons){
        int plus = 0;
        int profit = 0;
        for (int[] user: users){
            int s = user[0];
            int limit = user[1];
            int sum = 0;
            for (int i = 0; i < emoticons.length; i++){
                if (res[i] >= s){
                    sum += emoticons[i] * (100-res[i]) / 100;
                }
            }
            if (sum >= limit){
                plus++;
            } else {
                profit += sum;
            }
        }
        cand.add(new int[] {plus, profit});
    }
}