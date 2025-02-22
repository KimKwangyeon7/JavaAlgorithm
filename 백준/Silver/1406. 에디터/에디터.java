

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
        StringTokenizer st;
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        int cur = str.length();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	char c = st.nextToken().charAt(0);
        	if (c == 'L') {
        		if (cur != 0) {
        			cur--;
        		}
        	} else if (c == 'D') {
        		if (cur != sb.length()) {
        			cur++;
        		}
        	} else if (c == 'B') {
        		if (cur != 0) {
        			sb.deleteCharAt(cur-1);
        			cur--;
        		}
        	} else {
        		char tmp = st.nextToken().charAt(0);
        		if (cur == sb.length()) {
        			sb.append(tmp);
        		} else {
        			sb.insert(cur, tmp);
        		}
        		cur++;
        	}
        }
        System.out.println(sb.toString());
	}

}
