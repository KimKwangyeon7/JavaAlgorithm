import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = numbers.length-1; i >= 0; i--) {
            while (!stack.empty()) {
                if (stack.peek() <= numbers[i]) {
                    stack.pop();
                } else {
                    answer[i] = stack.peek();
                    break;
                }
            }

            if (stack.empty()) {
                answer[i] = -1;
            }
            stack.push(numbers[i]);
        }
        return answer;
    }
}