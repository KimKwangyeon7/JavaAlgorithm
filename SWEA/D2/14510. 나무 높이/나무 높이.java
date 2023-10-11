
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int max = 0; // 나무 높이의 최대값
			int two = 0; // 2로 나눴을 때 몫들의 합
			int one = 0; // 2로 나눴을 때 나머지들의 합
			int[] trees = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int a = Integer.parseInt(st.nextToken()); // 나무 높이 입력 받기
				max = Math.max(max, a);
				trees[i] = a;
			}
			int cha = 0;
			for (int i = 0; i < N; i++) {
				cha = max - trees[i];
				two += cha / 2;
				one += cha % 2;
			}
			int days = 0;
			//System.out.println(one + " " + two);
			if (one == two) {
				days = one + two;
				sb.append(days).append("\n");
			} else if (one > two) {
				days = (one-two)*2 - 1 + two*2;
				sb.append(days).append("\n");
 			} else {
 				int minus = two - one;
 				if (minus == 1) {
 					days = one+1 + two;
 					sb.append(days).append("\n");
 				} else {
 					while (two > one+1) {
 						two--;
 						one += 2;
 					}
 					if (two == one+1) {
 						days = one+two+1;
 						sb.append(days).append("\n");
 					}
 					else if (one == two) {
 						days = one + two;
 						sb.append(days).append("\n");
 					} else {
 						days = (one-two)*2 - 1 + two*2;
 						sb.append(days).append("\n");
 					}
 				}
 			}
			
		}
		System.out.println(sb);
	}
}