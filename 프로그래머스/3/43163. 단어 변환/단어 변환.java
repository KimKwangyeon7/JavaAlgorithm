class Solution {
    static int answer;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        dfs(0, begin, target, words);
        
        if (answer == Integer.MAX_VALUE){
            return 0;
        }
        return answer;
    }
    private static void dfs(int L, String str, String target, String[] words){
        if (str.equals(target)){
            answer = Math.min(answer, L);    
            return;
        }
        for (int i = 0; i < words.length; i++){
            if (!visited[i] && check(str, words[i])){
                visited[i] = true;
                dfs(L+1, words[i], target, words);
                visited[i] = false;
            }
        }
    }
    private static boolean check(String a, String b){
        int cnt = 0;
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)){
                cnt++;
            }
            if (cnt > 1){
                return false;
            }
        }
        return true;
    }
}