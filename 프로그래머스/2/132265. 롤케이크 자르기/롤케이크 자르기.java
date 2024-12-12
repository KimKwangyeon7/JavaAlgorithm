import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int[] dis = new int[10001];
        HashSet<Integer> left = new HashSet<>();
        for (int t: topping){
            dis[t]++;
        }
        int total = getNum(dis);
        for (int i = 0; i < topping.length-1; i++){
            dis[topping[i]]--;
            left.add(topping[i]);
            
            int leftNum = left.size();
            if (dis[topping[i]] == 0){
                total--;
            }
            if (leftNum == total){
                answer++;
            }
        }
        return answer;
    }
    private static int getNum(int[] dis){
        int cnt = 0;
        for (int d: dis){
            if (d > 0){
                cnt++;
            }
        }
        return cnt;
    }
}