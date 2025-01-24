
/**
 * @author kwang
 *
 */
import java.util.*;
import java.io.*;

public class Main {
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		if (N == 0) {
			
			int min = L / (M+1);
			if (L % (M+1) == 0) {
				System.out.println(min);
			} else {
				System.out.println(min+1);
			}
		} else {
			int[] dis = new int[N+2];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				dis[i] = Integer.parseInt(st.nextToken());	
			}
			dis[0] = 0;
			dis[N+1] = L;
			Arrays.sort(dis);
			int left = 1;
			int right = L-1;
			
			answer = Integer.MAX_VALUE;
			while (left <= right) {
				int mid = (left+right) / 2;
				if (check(mid, dis, M, L)) {
					right = mid-1;
					answer = Math.min(answer, mid);
				} else {
					left = mid+1;
				}
			}
			System.out.println(answer);
		}
	}
	private static boolean check(int gap, int[] dis, int total, int L) {
		int cnt = 0;
		int size = dis.length;

		for (int i = 1; i < size; i++) {
			int cha = dis[i] - dis[i-1];
			if (cha > gap) {
				cnt += (cha-1) / gap;
			}
		}
		if (cnt <= total) {
			return true;
		}
		return false;
	}

}
