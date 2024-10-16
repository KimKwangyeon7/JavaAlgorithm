import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        
        for (String op: operations){
            String[] tmp = op.split(" ");
            if (tmp[0].equals("I")){ // 숫자 삽입
                max.offer(Integer.parseInt(tmp[1]));
                min.offer(Integer.parseInt(tmp[1]));
            } else if (tmp[1].equals("1")){ // 최댓값 삭제        
                if (!max.isEmpty()){
                    int m = max.poll();
                    min.remove(m);
                }
            } else { // 최솟값 삭제
                if (!min.isEmpty()){
                    int m = min.poll();
                    max.remove(m);
                }
            }
        }
        if (max.isEmpty()){
            return answer;
        } 
        answer[0] = max.poll();
        answer[1] = min.poll();
        return answer;
    }
}