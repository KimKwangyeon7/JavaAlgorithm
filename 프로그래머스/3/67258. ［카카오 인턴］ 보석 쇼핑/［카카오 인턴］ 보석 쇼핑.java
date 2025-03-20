import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (String g: gems){
            set.add(g);
        }
        int N = set.size();
        
        int left = 0;
        int right = 0;
        int len = gems.length;
        int cnt = 0;
        int minLen = Integer.MAX_VALUE;
        while (right < len){
            String gem = gems[right];
            map.put(gem, map.getOrDefault(gem, 0) + 1);
            
            if (map.get(gem) == 1){
                cnt++;
            }
            
            while (cnt == N){
                int cha = right - left + 1;
                if (minLen > cha){
                    minLen = cha;
                    answer[0] = left+1;
                    answer[1] = right+1;
                }
                String leftGem = gems[left];
                map.put(leftGem, map.get(leftGem) - 1);
                if (map.get(leftGem) == 0){
                    cnt--;
                }
                left++;
            }
            right++;
        }
        return answer;
    }
}