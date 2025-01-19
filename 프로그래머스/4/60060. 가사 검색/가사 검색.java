import java.util.*;

class Solution {
    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        Map<Integer, Integer> lengthCount = new HashMap<>(); // 각 길이에 해당하는 단어 개수 저장
    }
    
    TrieNode root = new TrieNode();        // 정방향 트라이
    TrieNode reverseRoot = new TrieNode(); // 역방향 트라이

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        // 1. 정방향 및 역방향 트라이 구성
        for (String word : words) {
            insert(word, root, false);          // 정방향 삽입
            insert(new StringBuilder(word).reverse().toString(), reverseRoot, false); // 역방향 삽입
        }
        
        // 2. 쿼리 처리
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            
            // 물음표로만 이루어진 경우 처리
            if (query.charAt(0) == '?' && query.charAt(query.length() - 1) == '?') {
                answer[i] = root.lengthCount.getOrDefault(query.length(), 0);
            } 
            // 접미사 검색
            else if (query.charAt(0) == '?') {
                String reversedQuery = new StringBuilder(query).reverse().toString();
                answer[i] = search(reversedQuery, reverseRoot);
            } 
            // 접두사 검색
            else {
                answer[i] = search(query, root);
            }
        }
        
        return answer;
    }

    // 단어 삽입
    private void insert(String word, TrieNode node, boolean reversed) {
        int length = word.length();
        TrieNode current = node;
        
        // 단어의 길이 저장 (길이별 단어 개수)
        current.lengthCount.put(length, current.lengthCount.getOrDefault(length, 0) + 1);
        
        for (char c : word.toCharArray()) {
            current.child.putIfAbsent(c, new TrieNode());
            current = current.child.get(c);
            current.lengthCount.put(length, current.lengthCount.getOrDefault(length, 0) + 1);
        }
    }

    // 쿼리 검색
    private int search(String query, TrieNode node) {
        TrieNode current = node;
        int length = query.length();
        
        for (char c : query.toCharArray()) {
            if (c == '?') {
                // 와일드카드인 경우: 현재 노드에서 길이에 해당하는 단어 개수 반환
                return current.lengthCount.getOrDefault(length, 0);
            }
            
            if (!current.child.containsKey(c)) {
                // 현재 문자에 해당하는 경로가 없으면 0 반환
                return 0;
            }
            
            current = current.child.get(c);
        }
        
        return current.lengthCount.getOrDefault(length, 0);
    }
}
