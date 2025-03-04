import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0; // 가능한 스킬트리 개수

        for (String tree : skill_trees) {
            String filtered = tree.replaceAll("[^" + skill + "]", ""); // skill에 포함된 문자만 남김
            
            if (skill.startsWith(filtered)) { // 남은 문자열이 skill의 앞부분과 같은지 확인
                count++;
            }
        }

        return count;
    }
}