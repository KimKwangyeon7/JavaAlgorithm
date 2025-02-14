


import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			set.add(tmp);
		}

		int ans = 0;
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			if (set.contains(tmp)) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
