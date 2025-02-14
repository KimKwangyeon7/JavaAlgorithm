


import java.io.*;
import java.util.*;

public class Main {

    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean isLast;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            TrieNode root = new TrieNode();
            boolean isConsistent = true;  // 일관성 여부
            int flag = 0;
            //String[] numbers = new String[n];
            for (int i = 0; i < n; i++) {
                String number = br.readLine();
                if (!isConsistent) {
                	continue;
                }
                if (!insert(number, root)) {  // insert 과정에서 접두어 문제 발생 시 false 반환
                    isConsistent = false;
                }
            }

            // 입력 정렬 (사전순 정렬하면 접두어 여부를 더 빠르게 확인 가능)
//            Arrays.sort(numbers);
//
//            for (String number : numbers) {
//                if (!insert(number, root)) {  // insert 과정에서 접두어 문제 발생 시 false 반환
//                    isConsistent = false;
//                    break;
//                }
//            }

            sb.append(isConsistent ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean insert(String tmp, TrieNode root) {
        TrieNode cur = root;
        for (char c : tmp.toCharArray()) {
            if (!cur.child.containsKey(c)) {
                cur.child.put(c, new TrieNode());
            }
            cur = cur.child.get(c);

            if (cur.isLast) {
                return false; // 현재 경로가 이미 등록된 번호이면 일관성 깨짐
            }
        }

        // 현재 번호를 추가할 때, 이미 하위 노드가 있다면 접두어 문제 발생
        if (!cur.child.isEmpty()) {
            return false;
        }

        cur.isLast = true;
        return true;
    }
}
