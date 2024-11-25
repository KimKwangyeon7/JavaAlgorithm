import java.util.*;
class Solution {
    static boolean[] visited;
    public int solution(int[] cards) {
        int answer = Integer.MIN_VALUE;
        int len = cards.length;
        visited = new boolean[len+1];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++){
            if (!visited[cards[i]]){
                list.add(check(cards[i], cards));
            }
        }
        Collections.sort(list);
        int size = list.size();
        if (list.size() < 2){
            return 0;
        }
        return list.get(size-1) * list.get(size-2);
    }
    static int check(int card, int[] cards){
        int len = cards.length;
        int now = cards[card-1];
        visited[card] = true;
        int cnt = 1;
        while (true){
            if (visited[now]){
               break;
            } 
            visited[now] = true;
            cnt++;
            now = cards[now-1];
        }
        return cnt;
    }
 }