

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
//		F = 1000000;
//		S = 1;
//		G = 1000000;
//		U = 2;
//		D = 1;
		if (S == G) {
			System.out.println(0);
		} else {
			if (U == 0 && G > S) {
				System.out.println("use the stairs");
			} else if (D == 0 && S > G) {
				System.out.println("use the stairs");
			} else {
				Queue<int[]> qu = new ArrayDeque<>();
				int[] button = new int[2];
				button[0] = U;
				button[1] = -D;
				boolean[] visited = new boolean[F+1];
				qu.offer(new int[] {S, 0});
				int flag = -1;
				visited[S] = true;
				while (!qu.isEmpty()) {
					int[] tmp = qu.poll();
					
					if (tmp[0] == G) {
						flag = tmp[1];
						break;
					}
					for (int i = 0; i < 2; i++) {
						int next = tmp[0] + button[i];
						
						if (next < 1 || next > F || visited[next]) {
							continue;
						}
						visited[next] = true;
						qu.offer(new int[] {next, tmp[1]+1});
					}
				}
				if (flag == -1) {
					System.out.println("use the stairs");
				} else{
					System.out.println(flag);
				}
			}
		}
	}

}
