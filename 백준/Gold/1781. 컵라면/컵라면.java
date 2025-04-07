

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
        Queue<int[]> qu = new PriorityQueue<>((o1, o2) -> {
        	if (o1[0] == o2[0]) {
        		return Integer.compare(o2[1], o1[1]);
        	}
        	return Integer.compare(o1[0], o2[0]);
        });
        
        Queue<int[]> qu2 = new PriorityQueue<>((o1, o2) -> {
        	return Integer.compare(o1[1], o2[1]);
        });
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int d = Integer.parseInt(st.nextToken());
        	int r = Integer.parseInt(st.nextToken());
        	qu.offer(new int[] {d, r});
        }
        int time = 0;
        int answer = 0;
        while (!qu.isEmpty()) {
        	int[] prob = qu.poll();
        	if (time+1 <= prob[0]) {
        		time++;
        		answer += prob[1];
        		qu2.offer(prob);
        	} else {
        		int[] minProb = qu2.peek();
        		if (minProb[1] < prob[1]) {
        			answer -= minProb[1];
        			answer += prob[1];
        			qu2.poll();
        			qu2.offer(prob);
        		}
        	}
        }
        System.out.println(answer);
	}

}
