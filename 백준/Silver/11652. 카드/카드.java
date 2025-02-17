

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
        Map<Long, Integer> map = new TreeMap<>();
        
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
        	long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        int max = 0;
        long ans = 0;
        for (long n: map.keySet()) {
        	if (map.get(n) > max) {
        		ans = n;
        		max = map.get(n);
        	}
        }
        System.out.println(ans);
	}

}
