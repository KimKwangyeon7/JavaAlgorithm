import java.util.*;
class Solution {
    static int answer;
    static HashSet<HashSet> ans;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        HashSet<String> set = new HashSet<>();
        ans = new HashSet<>();
        dfs(set, user_id, banned_id, 0);
        
        return ans.size();
    }
    static void dfs(HashSet<String> set, String[] user_id, String[] banned_id, int L){
        if (L == banned_id.length){
            ans.add(set);
            return;
        }
        for (int i = 0; i < user_id.length; i++){
            if (set.contains(user_id[i])){
                continue;
            }
            if (check(user_id[i], banned_id[L])){
                set.add(user_id[i]);
                dfs(new HashSet<>(set), user_id, banned_id, L+1);
                set.remove(user_id[i]);
            }
        }
    }
    static boolean check(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }
   
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) == '*'){
                continue;
            } else {
                if (bannedId.charAt(i) != userId.charAt(i)){
                    return false;
                }
            }
        }
        return true;
    }
}