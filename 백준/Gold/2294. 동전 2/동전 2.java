

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<Integer> coins = new TreeSet<>();
		int[] val = new int[M+1];
		
		Arrays.fill(val, Integer.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			coins.add(Integer.parseInt(br.readLine()));
		}

		val[0] = 0;
		for (int coin: coins) {
			for (int i = coin; i <= M; i++) {
				if (val[i-coin] != Integer.MAX_VALUE) {
					val[i] = Math.min(val[i], val[i-coin]+1);
				}
			}
		}
		if (val[M] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(val[M]);
		}
		
	}

}
