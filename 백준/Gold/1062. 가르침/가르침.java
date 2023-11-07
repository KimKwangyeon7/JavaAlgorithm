
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static int N, K;
	static int ans;
	static boolean[] alpha;
	static String[] str;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		ans = 0;
		str = new String[N];
		for (int i = 0; i < N; i ++) {
			String tmp = br.readLine();
			str[i] = tmp.substring(4, tmp.length()-4);
		}	
		
//		for (String s: str) {
//			System.out.println(s);
//		}
		if (K < 5) {
			System.out.println(0);
		} else if (K == 26) {
			System.out.println(N);
		} else {
			reset();
			DFS(0, 0);
			System.out.println(ans);
		}
	}

	private static void reset() {
		alpha = new boolean[26];
		// t, i, c, a, n => 무조건 필요
		alpha[0] = true;
		alpha[2] = true;
		alpha[8] = true;
		alpha[13] = true;
		alpha[19] = true;
	}
	private static void DFS(int L, int start) {
		if (L == K-5) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				int len  = str[j].length();
				int flag = 0;
				for (int i = 0; i < len; i++) {
					if (!alpha[str[j].charAt(i)-'a']) {
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
			return;
		}
		for (int i = start; i < 26; i++) {
			if (!alpha[i]) {
				alpha[i] = true;
				DFS(L+1, i);
				alpha[i] = false;
			}
		}
	} 

}
