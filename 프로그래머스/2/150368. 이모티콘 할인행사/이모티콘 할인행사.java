class Solution {
    static int[] sale = {10, 20, 30, 40};
    static int[] res;
    static int[] answer;
    static int[] emo;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        res = new int[emoticons.length];
        emo = emoticons.clone();
        dfs(0, emoticons.length, users);
        return answer;
    }
    private static void dfs(int L, int n, int[][] users){
        if (L == n){
            // for (int i = 0; i < res.length; i++){
            //     System.out.print(res[i] + " ");
            // }
            // System.out.println();
            check(users);
            return;
        }
        for (int i = 0; i < 4; i++){
            res[L] = sale[i];
            dfs(L+1, n, users);
        }
    }
    private static void check(int[][] users){
        int len = users.length;
        int cnt = 0;
        int total = 0;
        for (int i = 0; i < len; i++){
            int sum = 0;
            for (int j = 0; j < res.length; j++){
                if (res[j] >= users[i][0]){
                    sum += emo[j] * (100 - res[j]) / 100;
                }
            }
            if (sum >= users[i][1]){
                cnt++;
            } else {
                
                total += sum;
            }
        }
        if (cnt > answer[0]){
            answer[0] = cnt;
            answer[1] = total;
        } else if (cnt == answer[0]){
            if (total > answer[1]){
                answer[1] = total;
            }
        }
    }
}