

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N = br.readLine();
		final int MOD = 1000000;
		int target = N.length();
		
		long[] dp = new long[5001];
		int first = Integer.parseInt(N.charAt(0)+"");
		if (first != 0) {
			dp[0] = 1L;
		}
		for (int i = 1; i < target; i++) {
			int one = Integer.parseInt(N.charAt(i)+"");
			int sec = Integer.parseInt(N.substring(i-1, i+1));
			//System.out.println(sec);
			if (one != 0) {
				dp[i] += dp[i-1]%MOD;
			}
			if (sec >= 10 && sec < 27) {
				if (i == 1) {
					dp[i] += 1L;
				} else {
					dp[i] += dp[i-2]%MOD;
				}
			}
		}
		System.out.println(dp[target-1]%MOD);
	}

}
