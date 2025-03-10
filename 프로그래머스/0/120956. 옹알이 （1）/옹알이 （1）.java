import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int count = 0;
        String[] words = {"aya", "ye", "woo", "ma"};

        for (String str : babbling) {
            Set<String> used = new HashSet<>();
            boolean isValid = true;
            
            for (String word : words) {
                if (str.contains(word)) {
                    if (used.contains(word)) {
                        isValid = false; // 같은 단어가 두 번 나오면 안 됨
                        break;
                    }
                    used.add(word);
                    str = str.replaceFirst(word, " "); // 단어를 제거
                }
            }

            if (str.trim().isEmpty() && isValid) {
                count++;
            }
        }
        return count;
    }
}
