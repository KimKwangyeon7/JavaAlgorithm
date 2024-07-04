import java.util.*;
class Solution {
    static int res;
    static int[] ans = new int[11];
    static int[] answer;
    public int[] solution(int n, int[] info) {
        res = 0;
      
        dfs(0, n, info);
        
        if (res == 0){
            return new int[] {-1};
        } else {
            return answer;
        }
    }
    private static void dfs(int L, int n, int[] info) {
    	if (L == n) {
    		int a = 0;
            int b = 0;
            for (int j = 0; j < 11; j++){
                if (info[j] == 0 && ans[j] == 0){
                    continue;
                } else if (info[j] >= ans[j]){
                    a += 10-j;
                } else {
                    b += 10-j;
                }
            }
            if (b > a){
                int cha = b-a;
                if (cha >= res){
                    res = cha;
                    answer = Arrays.copyOf(ans, 11);
                }
            }
    		return;
    	}
    	
    	for (int i = 0; i < 11 && ans[i] <= info[i]; i++) {
            ans[i] += 1;
            dfs(L+1, n, info);
            ans[i] -= 1;  
    	}
    }
}