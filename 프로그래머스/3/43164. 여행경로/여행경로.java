import java.util.*;
class Solution {

    static int flag = 0;
    static boolean[] visited;
    static String[] answer;
    static List<String> list = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        //res = new int[N];
        //visited[] = true;
        //Arrays.sort(tickets);
        dfs(0, "ICN", "ICN", tickets.length, tickets);
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }
    private static void dfs(int L, String prev, String order, int N, String[][] tickets){
        if (L == N){
            //flag = 1;
            list.add(order);
            return;
        }
        for (int i = 0; i < tickets.length; i++){
            if (tickets[i][0].equals(prev) && !visited[i]){
                visited[i] = true;
                dfs(L+1, tickets[i][1], order + " " + tickets[i][1], N, tickets);
                visited[i] = false;
                
                // if (flag == 1){
                //     return;
                // }
            }
        }
    }
}