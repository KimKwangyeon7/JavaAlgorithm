

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        long total = 0;
        long cnt = 9;
    	int len = 1;
    	
    	while (K > total + len*cnt) {
    		total += len*cnt;
    		cnt *= 10;
    		len++;
    	}
        
    	check(len, K, total, N);
        
    	
        
	}

	private static void check(int digit, int K, long sum, int N) {
		long cha = K - sum;
		//System.out.println(cha);
		long val = cha / digit;
		int r = (int) cha % digit;
		long res = (long) Math.pow(10, digit-1) + (val-1);
		//System.out.println(res);
		if (r != 0) {
			res += 1;
		}
		if (res > N) {
			System.out.println(-1);
			return;
		}
		String str = res+"";
		if (r == 0) {
			
			System.out.println(str.charAt(digit-1));
		} else {
			System.out.println(str.charAt(r-1));
		}
	}
}
