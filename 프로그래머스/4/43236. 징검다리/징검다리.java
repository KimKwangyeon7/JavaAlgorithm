import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int ansSection = rocks.length - n + 1;
        
        int left = 1; 
        int right = distance;
        int answer = distance;
        while (left <= right){
            int mid = (left + right) / 2;
            
            if ((distance / mid) < ansSection){
                right = mid - 1;
                continue;
            }
            
            if (check(distance, mid, rocks, ansSection)){
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(int distance, int section, int[] rocks, int ansSection){
        int min = 0;
        int sectionCnt = 0;
        
        for (int rock: rocks){
            int dist = rock - min;
            
            if ((dist / section) < 1)
                continue;
            sectionCnt++;
            min = rock;
        }
        
        int lastDist = distance - min;
        if ((lastDist/section) >= 1)
            sectionCnt++;
               
        if (sectionCnt >= ansSection)
            return true;
        return false;
    }
}