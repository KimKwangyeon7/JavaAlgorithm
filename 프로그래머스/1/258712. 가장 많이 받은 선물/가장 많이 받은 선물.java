import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int N = friends.length;
        int[][] board = new int[N][N];
        int[] get = new int[N];
        int[] give = new int[N];
        int[] res = new int[N];
        Map<String, Integer> name = new HashMap<>();
        for (int i = 0; i < N; i++){
            name.put(friends[i], i);
        }
        
        for (String g: gifts){
            String[] tmp = g.split(" ");
            board[name.get(tmp[0])][name.get(tmp[1])]++;
            give[name.get(tmp[0])]++;
            get[name.get(tmp[1])]++;
        }
        
        for (int i = 0; i < N; i++){
            for (int j = i+1; j < N; j++){
                if (board[i][j] > board[j][i]){
                    res[i]++;
                } else if (board[i][j] < board[j][i]){
                    res[j]++;
                } else {
                    int tmpI = give[i] - get[i];
                    int tmpJ = give[j] - get[j];
                    
                    if (tmpI > tmpJ){
                        res[i]++;
                    } else if (tmpI < tmpJ){
                        res[j]++;
                    }
                }
            }
        }
        for (int r: res){
            answer = Math.max(answer, r);   
        }
        
        return answer;
    }
}