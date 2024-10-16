import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (s < n){
            return new int[] {-1};
        }
        int m = s / n;
        int r = s % n;
        
        Arrays.fill(answer, m);
        for (int i = n-r; i < n; i++){
            answer[i]++;
        }
        return answer;
    }
}