import java.util.ArrayList;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] array = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            int day = 0;
            while (progresses[i] < 100) {
                progresses[i] += speeds[i];
                day++;
            }
            array[i] = day;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int cur = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (cur >= array[i]) {
                count++;
            } else {
                list.add(count);
                cur = array[i];
                count = 1;
            }
        }
        list.add(count);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}