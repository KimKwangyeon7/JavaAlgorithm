

import java.io.*;
import java.util.*;

public class Main {

    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean isLast = false;
        int cnt = 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        TrieNode root = new TrieNode();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String alias = search(str, root);
            sb.append(alias).append("\n");
            insert(str, root);
        }
        System.out.print(sb);
    }

    private static String search(String str, TrieNode root) {
        TrieNode cur = root;
        StringBuilder res = new StringBuilder();
        
        for (char c : str.toCharArray()) {
            res.append(c);
            if (!cur.child.containsKey(c)) {
                return res.toString(); // 접두사가 이전 닉네임과 겹치지 않으면 사용 가능
            }
            cur = cur.child.get(c);
        }

        // 중복된 닉네임이라면 숫자를 붙여야 함
        if (cur.cnt == 0) {
        	return res.toString();
        } else {
        	return res.append(cur.cnt+1).toString();
        }
        
    }

    private static void insert(String str, TrieNode root) {
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            cur.child.putIfAbsent(c, new TrieNode());
            cur = cur.child.get(c);
        }
        cur.isLast = true;
        cur.cnt++;
    }
}

