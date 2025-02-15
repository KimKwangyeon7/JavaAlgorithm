
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
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	char[] order = br.readLine().toCharArray();
        	int n = Integer.parseInt(br.readLine());
        	String tmp = br.readLine();
        	Deque<String> dq = new ArrayDeque<>();
        	if (n != 0) {
        		tmp = tmp.substring(1, tmp.length()-1);
            	String[] arr = tmp.split(",");
            	
            	for (String a: arr) {
            		dq.offer(a);
            	}
        	}
        	int revCnt = 0;
        	int flag = 0;
        	for (char c: order) {
        		if (c == 'R') {
        			revCnt++;
        		} else {
        			if (dq.isEmpty()) {
        				//System.out.println(t + " " + c);
        				sb.append("error").append("\n");
        				flag = 1;
        				break;
        			}
        			if (revCnt % 2 == 0 ) {
        				dq.pollFirst();
        			} else {
        				dq.pollLast();
        			}
        		}
        	}
        	if (flag == 0) {
        		sb.append("[");
        		if (revCnt % 2 == 0 ) {
    				while (!dq.isEmpty()) {
    					sb.append(dq.pollFirst());
    					if (dq.isEmpty()) {
    						break;
    					} else {
    						sb.append(",");
    					}
    				}
    			} else {
    				while (!dq.isEmpty()) {
    					sb.append(dq.pollLast());
    					if (!dq.isEmpty()) {
    						sb.append(",");
    					} 
    				}
    			}
        		sb.append("]").append("\n");
        	}
        }
        System.out.print(sb);
	}

}
