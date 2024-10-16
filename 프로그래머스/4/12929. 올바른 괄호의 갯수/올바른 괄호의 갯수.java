import java.util.*;
class Solution {
    static int answer;
    public int solution(int n) {
        answer = 0;
        
        dfs(0, 0, 0, n, "");
        return answer;
    }
    private static void dfs(int L, int start, int end, int n, String str){
        if (start < end){
            return;
        }
        if (start > n){
            return;
        }
        if (L == n*2){
            if (check(str)){
                answer++;
            }
            return;
        }
        String tmp = str;
        dfs(L+1, start+1, end, n, tmp+"{");
        dfs(L+1, start, end+1, n, tmp+"}");
    }
    private static boolean check(String str){
        Stack<Character> st = new Stack<>();
        char[] a = str.toCharArray();
        for (char tmp: a){
            if (tmp == '{'){
                st.push(tmp);
            } else {
                if (st.isEmpty()){
                    return false;
                }
                st.pop();
            }
        }
        if (st.isEmpty()){
            return true;
        }
        return false;
    }
}