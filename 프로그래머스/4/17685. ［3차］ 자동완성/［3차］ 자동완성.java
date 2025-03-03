import java.util.*;

class Solution {
    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        int cnt = 1;
    }
    public int solution(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words){
            insert(root, word);
        }
        int ans = 0;
        for (String word: words){
            ans += find(root, word);
        }
        return ans;
    }
    private static void insert(TrieNode root, String word){
        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.child.containsKey(c)){
                cur.child.get(c).cnt++;
            } else {
                cur.child.put(c, new TrieNode());
            }
            cur = cur.child.get(c);
        }
    }
    private static int find(TrieNode root, String word){
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.child.get(c).cnt == 1){
                //System.out.println(word + " " + c + " " + (i+1));
                return i+1;
            }
            cur = cur.child.get(c);
        }
        //System.out.println(word + " " + word.length());
        return word.length();
    }
}