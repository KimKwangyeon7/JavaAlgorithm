class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int N  = players.length;
        int[] dis = new int[N];
        
        for (int i = 0; i < N; i++){
            int p = players[i];
            int needs = p / m;
            
            int add = needs - dis[i];
            if (add < 0){
                continue;
            }
            answer += add;
            for (int j = i; j < i+k && j < N; j++){
                dis[j] += add;
            }
        }
        
        return answer;
    }
}