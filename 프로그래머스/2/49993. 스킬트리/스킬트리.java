import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String str: skill_trees){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++){
                if (skill.contains("" + str.charAt(i))){
                    sb.append(str.charAt(i));
                }
            }
            if (skill.indexOf(sb.toString()) == 0){
                //System.out.println(sb.toString());
                answer++;
            }
        }
        
        
        return answer;
    }
}