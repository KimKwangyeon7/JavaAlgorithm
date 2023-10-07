import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static int[] pi;
	static String P;
	static int len;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String T = br.readLine();
		P = br.readLine();
		
		len = P.length();
		pi = new int[len];
		//Arrays.fill(pi, 0);
//		System.out.println(Arrays.toString(pi));
//		System.out.println(P.charAt(0));
//		System.out.println(len);
		if (len >= 2) {
			fillArray();
		}
		int l = T.length();
		
		int j = 0;
		int cnt = 0;
		List<Integer> list = new ArrayList<>();
		
		if (len >= 2) {
			for (int i = 0; i < l; i++) {
				while (j > 0 && T.charAt(i) != P.charAt(j)) {
					j = pi[j-1];
				}
				if (T.charAt(i) == P.charAt(j)) {
					if (j == len-1) {
						cnt++;
						list.add(i-len+2);
						j = pi[j];
					} else {
						j++;
					}
				}
			}	
		} else {
			for (int n = 0; n < l; n++) {
				if (T.charAt(n) == P.charAt(0)) {
					list.add(n+1);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		int u = list.size();
		for (int m = 0; m < u; m++) {
			if (m == u-1) {
				System.out.print(list.get(m));
			}
			else {
				System.out.print(list.get(m)+" ");
			}
		}	
	}
	static void fillArray() { // 부분 일치 테이블도 역시 KMP 알고리즘 적용
		int j = 0;
		int idx = 0;
		for (int i = 1; i < len; i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = pi[j-1];
			}
			if (P.charAt(i) == P.charAt(j)) {
				j++;
				pi[i] = j;
			}
		}
	}
}
