import java.util.*;
class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        // for (String word: words){
        //     StringBuilder sb = new StringBuilder();
        //     for (int i = 0; i < word.length(); i++){
        //         sb.append(word.charAt(i));
        //         map.put(sb.toString(), map.getOrDefault(sb.toString(), 0)+1);
        //     }
        // }
        // for (String word: words){
        //     StringBuilder sb = new StringBuilder();
        //     for (int i = 0; i < word.length(); i++){
        //         sb.append(word.charAt(i));
        //         int tmp = map.get(sb.toString());
        //         if (tmp == 1){
        //            answer += sb.toString().length();
        //             break;
        //         }
        //         if (i == word.length()-1){
        //             answer += sb.toString().length();
        //         }
        //     }
        // }
        Arrays.sort(words);
        int idx = 0;
        int prev = -1;
        for (int i = 0; i < words.length-1; i++){
            String a = words[i];
            String b = words[i+1];
            idx = 0;
            int flag = 0;
            while (idx < a.length() && idx < b.length()){
                if (a.charAt(idx) != b.charAt(idx)){
                    flag = 1;
                    if (prev > idx){
                        answer += prev+1;
                    } else {
                        answer += idx+1;
                    }
                    prev = idx;
                    break;
                }
                idx++;
            }
            if (flag != 1){
                idx = Math.min(a.length(), b.length());
                if (prev > idx){
                    answer += prev+1;
                } else {
                    answer += idx;
                }
                prev = idx;
            }
            if (i == words.length-2){
                answer += prev+1;
            }
        }
        return answer;
    }
}