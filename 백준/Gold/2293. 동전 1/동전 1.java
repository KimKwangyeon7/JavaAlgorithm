

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		int[] val = new int[M+1];
		
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		val[0] = 1;
		for (int coin: coins) {
			for (int i = coin; i < M+1; i++) {
				val[i] += val[i-coin];
			}
		}
		System.out.println(val[M]);
	}

}
