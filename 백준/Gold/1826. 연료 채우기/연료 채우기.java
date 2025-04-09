
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int loc = Integer.parseInt(st.nextToken());
        	int fuel = Integer.parseInt(st.nextToken());
        	list.add(new int[] {loc, fuel});
        }
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
    	int P = Integer.parseInt(st.nextToken());
    	list.add(new int[] {L, 0});
    	Collections.sort(list, (o1, o2) -> Integer.compare(o1[0], o2[0]));
    	
    	int start = 0;
    	int answer = 0;
    	Queue<int[]> qu = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
    	int idx = 0;
    	boolean flag = false;
    	while (idx < list.size()) {
    		int[] station = list.get(idx);
    		if (station[0] - start <= P) {
    			qu.offer(station);
    			idx++;
    		} else {
    			if (qu.isEmpty()) {
    				flag = true;
    				break;
    			}
    			int[] next = qu.poll();
    			answer++;
    			if (next[0] < start) {
    				P += next[1];
    			} else {
    				P = P - (next[0] - start) + next[1];
    				start = next[0];
    			}
    		}
    	}
    	System.out.println((flag)? -1: answer);
	}

}
