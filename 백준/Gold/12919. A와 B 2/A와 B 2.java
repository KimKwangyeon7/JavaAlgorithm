

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * @author kwang
 *
 */
public class Main {
	static String S, T;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
		
		HashSet<String> set = new HashSet<>();
		dfs(T.length(), T, S.length(), set);
		System.out.println("0");
	}

	private static void dfs(int L, String str, int M, HashSet<String> set) {
		if (L < M) {
			return;
		}
		if (L == M) {
			if (str.equals(S)) {
				System.out.println("1");
				System.exit(0);
			}
		}
//		if (set.contains(str+"A")) {
//			return;
//		} else {
//			set.add(str+"A");
//			dfs(L+1, str+"A", M, set);
//		}
//		if (set.contains(reverse(str+"B"))) {
//			return;
//		} else {
//			set.add(reverse(str+"B"));
//			dfs(L+1, reverse(str+"B"), M, set);
//		}
		
		if (str.charAt(str.length()-1) == 'A') { // 문자열의 마지막 문자가 A인 경우
			String tmp = str.substring(0, str.length()-1);
			if (!set.contains(tmp)) {
				set.add(tmp);
				dfs(L-1, tmp, M, set);
			}
		}
		
		if (str.charAt(0) == 'B') { // 문자열의 첫 문자가 B인 경우
			String tmp = reverse(str.substring(1, str.length()));
			if (!set.contains(tmp)) {
				set.add(tmp);
				dfs(L-1, tmp, M, set);
			}
		}
	}
	
	private static String reverse(String str) {
		String a = "";
		for (int i = str.length()-1; i >= 0; i--) {
			a += str.charAt(i);
		}
		return a;
	}

}
