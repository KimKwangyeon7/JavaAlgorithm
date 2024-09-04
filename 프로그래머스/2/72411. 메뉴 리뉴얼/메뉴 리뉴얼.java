import java.util.*;
class Solution {
    static int[] alpha;
    static HashSet<Character> set;
    static PriorityQueue<String> pq;
    static int M;
    static List<String> list;
    public String[] solution(String[] orders, int[] course) {
        
        pq = new PriorityQueue<>(); 
        set = new HashSet<>();
        for (String o: orders){
            for (int i = 0; i < o.length(); i++){
                set.add(o.charAt(i));
            }
        }
        
        for (int c: course){
            alpha = new int[c];
            M = Integer.MIN_VALUE;
            list = new ArrayList<>();
            combi(0, 0, c, orders);
            for (String a: list){
                pq.offer(a);
            }
        }
        
        String[] answer = new String[pq.size()];
        int cnt = 0;
        while (!pq.isEmpty()){
            answer[cnt++] = pq.poll();
        }

        return answer;
    }
    private static void combi(int L, int start, int N, String[] orders){
        if (L == N){
            
            int io = isOkay(orders);
            if (io >= 2){
                String str = "";
                for (int a: alpha){
                    str += (char)((int)'A' + a);
                }
                if (io > M){
                    list.clear();
                    list.add(str);
                    M = io;
                } else if (io == M){
                    list.add(str);
                }
            }
            
            return;
        }
        for (int i = start; i < 26; i++){
            char tmp = (char)((int)'A' + i);
            if (set.contains(tmp)){
                alpha[L] = i;
                combi(L+1, i+1, N, orders);
            }
        }
    }
    private static int isOkay(String[] orders){
        int cnt = 0;
        for (String order: orders){
            int sum = 0;
            for (int i = 0; i < alpha.length; i++){
                String tmp = "";
                tmp += (char)((int)'A' + alpha[i]);
                if (!order.contains(tmp)){
                    sum = 1;
                    break;
                }
            }
            if (sum == 0){
                cnt++;
            }
        }
        return cnt;
    }
}