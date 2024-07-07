
import java.util.*;

class Solution {
    static class Dot implements Comparable<Dot> {
        private int to;
        private int time;
        
        public Dot(int to, int time){
            this.to = to;
            this.time = time;
        }
        
        public int compareTo(Dot o){
            return Integer.compare(time, o.time);
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int N = gates.length;
        int M = summits.length;
        
        List<Dot>[] list = new ArrayList[n+1];
		for (int i = 1; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < paths.length; i++) {	
            if (checkGate(paths[i][0], gates) || checkSummit(paths[i][1], summits)){
                list[paths[i][0]].add(new Dot(paths[i][1], paths[i][2]));
            } else if (checkGate(paths[i][1], gates) || checkSummit(paths[i][0], summits)){
                list[paths[i][1]].add(new Dot(paths[i][0], paths[i][2]));
            } else {
			    list[paths[i][0]].add(new Dot(paths[i][1], paths[i][2])); 
			    list[paths[i][1]].add(new Dot(paths[i][0], paths[i][2]));
            }
		}
        
        PriorityQueue<Dot> pq = new PriorityQueue<>();
        int[] dis = new int[n+1]; // 최소값 저장할 배열
        Arrays.fill(dis,  Integer.MAX_VALUE); 
        
        for (int i = 0; i < N; i++){
            pq.offer(new Dot(gates[i], 0));
            dis[gates[i]] = 0;
        }

        //boolean[] visited = new boolean[n+1]; // 방문 체크할 배열

        while (!pq.isEmpty()) {
            Dot tmp = pq.poll();

            if (tmp.time > dis[tmp.to]){
                continue;
            }
            for (int j = 0; j < list[tmp.to].size(); j++) { 
                Dot next = list[tmp.to].get(j);
                if (Math.max(dis[tmp.to], next.time) < dis[next.to]) { 
                    dis[next.to] = Math.max(dis[tmp.to], next.time);
                    pq.offer(new Dot(next.to, dis[next.to]));
                }
            }
	    }
        int[] answer = {-1, Integer.MAX_VALUE};
        Arrays.sort(summits);
        for (int s: summits){
            if (answer[1] > dis[s]){
                answer[1] = dis[s];
                answer[0] = s;
            }
        }
        
        
        
	    return answer;
	}
	    private static boolean checkSummit(int x, int[] summits){
	        for (int i = 0; i < summits.length; i++){
	            if (summits[i] == x){
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    private static boolean checkGate(int x, int[] gates){
	        for (int i = 0; i < gates.length; i++){
	            if (gates[i] == x){
	                return true;
	            }
	        }
	        return false;
	    }
}


