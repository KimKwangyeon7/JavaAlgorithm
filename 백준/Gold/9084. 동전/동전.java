
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			int[] val = new int[10001];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			val[0] = 1;
			for (int coin: coins) {
				for (int i = coin; i < M+1; i++) {
					val[i] += val[i-coin];
				}
			}
			sb.append(val[M]).append("\n");
		}
		System.out.println(sb);
	}

}
