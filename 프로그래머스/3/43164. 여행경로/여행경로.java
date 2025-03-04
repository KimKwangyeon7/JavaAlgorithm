import java.util.*;
class Solution {

    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++){
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            map.putIfAbsent(from, new PriorityQueue<>());
            map.get(from).offer(to);
        }
        LinkedList<String> list = new LinkedList<>();
        dfs("ICN", map, list);
        
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
    private static void dfs(String port, Map<String, PriorityQueue<String>> map, LinkedList<String> list){
        PriorityQueue<String> destinations = map.get(port);
        
        while (destinations != null && !destinations.isEmpty()) {
            String nextAirport = destinations.poll(); 
            dfs(nextAirport, map, list);
        }
        
        list.addFirst(port);  
    }
}