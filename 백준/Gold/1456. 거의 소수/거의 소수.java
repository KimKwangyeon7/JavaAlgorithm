

import java.io.*;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int cnt;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int len = (int)Math.sqrt(B);
        
        boolean[] visited = new boolean[len+1];
        Arrays.fill(visited, true);
        visited[0] = true;
        visited[1] = true;
        
        for (int i = 2; i*i <= len; i++) {
        	if (visited[i]) {
        		for (int j = i*i; j <= len; j += i) {
        			visited[j] = false;
        		}
        	}
        }
        cnt = 0;
        for (int i = 2; i <= len; i++) {
        	if (visited[i]) {
        		int idx = 2;
        		while (true) {
        			long tmp = (long)Math.pow(i, idx);
        			if (tmp < A) {
        				idx++;
        			} else if (tmp > B) {
        				break;
        			} else {
        				idx++;
        				cnt++;
        			}
        		}
        	}
        }
        System.out.println(cnt);
	}
	
}
