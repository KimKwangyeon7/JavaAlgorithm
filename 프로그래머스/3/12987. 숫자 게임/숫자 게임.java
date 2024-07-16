import java.util.*;
class Solution {
    static LinkedList<Integer> list;
    static int len;
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(B);
        Arrays.sort(A);
        int a = 0;
        int b = 0;
        for (int i = 0; i < A.length; i++){
            if (A[a] >= B[b]){
                b++;
            } else {
                a++;
                b++;
                answer++;
            }
        }
        // list = new LinkedList<>();
        // for (int b: B){
        //     list.add(b);
        // }
        // len = list.size()-1;
        // for (int i: A){
        //     if (list.getLast() <= i){
        //         //int tmp = list.getLast();
        //         list.removeFirst();
        //         len--;
        //         continue;
        //     }
        //     answer += find(i);
        // }
        
        
        return answer;
    }
        
    public int find(int x){     
        for (int m = 0; m <= len; m++){
            if (list.get(m) > x){
                list.remove(m);
                len--;
                return 1;
            }
        }
        list.removeFirst();
        len--;
        return 0;
    }
}