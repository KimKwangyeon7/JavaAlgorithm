import java.util.*;
class Solution {
    static Map<Character, Integer> map;
    static boolean[] visited;
    static int[] res;
    static int answer;
    public int solution(int n, String[] data) {
        answer = 0;
        map = new HashMap<>();
        char[] name = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        for (int i = 0; i < 8; i++){
            map.put(name[i], i);
        }
        res = new int[8];
        visited = new boolean[8];
        dfs(0, data);
        
        return answer;
    }
    private boolean check(int[] order, String[] data){
        for (String d: data){
            int name1 = map.get(d.charAt(0));
            int name2 = map.get(d.charAt(2));
            int order1 = 0;
            int order2 = 0;
            int cnt = 0;
            for (int i = 0; i < 8; i++){
                if (cnt == 2){
                    break;
                }
                if (order[i] == name1){
                    cnt++;
                    order1 = i;
                } else if (order[i] == name2){
                    cnt++;
                    order2 = i;
                }
            }
            int num = d.charAt(4) - '0';
            if (d.charAt(3) == '='){
                if (Math.abs(order1-order2)-1 != num){
                    return false;
                } 
            } else if (d.charAt(3) == '>'){
                if (Math.abs(order1-order2)-1 <= num){
                    return false;
                } 
            } else {
                if (Math.abs(order1-order2)-1 >= num){
                    return false;
                } 
            }
        }
        return true;
    }
    private void dfs(int L, String[] data){
        if (L == 8){
            int[] tmp = res.clone();
            if (check(tmp, data)){
                answer++;
            }
            return;
        }
        for (int i = 0; i < 8; i++){
            if (!visited[i]){
                visited[i] = true;
                res[L] = i;
                dfs(L+1, data);
                visited[i] = false;
            }
        }
    }
}