import java.util.*;
 
class Solution {
    public int solution(int[] a) {
        int len = a.length;
        int[] left = new int[len]; 
        int[] right = new int[len]; 
        int l = a[0]; 
        int r = a[len - 1];
        
        //i일때 왼쪽 원소의 최소값을 저장
        for(int i = 1; i < len - 1; i++) {
            if(l > a[i]){
                l = a[i];    
            } 
            left[i] = l;
        }
        //i일때 오른쪽 원소의 최소값을 저장
        for(int i = a.length - 2; i > 0; i--) {
            if(r > a[i]){
                r = a[i];    
            } 
            right[i] = r;
        }
        
        if(a.length == 1){
            return 1; //원소가 1개면 답은 1이다.
        } 
        int answer = 2; // 반환할 값. 원소가 2개 이상일때 무조건 2개 이상은 남게된다.
        for(int i = 1; i <= a.length - 2; i++) {
            if(a[i] > left[i] && a[i] > right[i]){
                continue;
            }
            answer++;
        }
        return answer;
    }
}