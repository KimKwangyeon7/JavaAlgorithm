
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SSAFY
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String W = br.readLine(); // 주어진 문자열
			int K = Integer.parseInt(br.readLine()); // 포함할 어떤 문자의 개수
			
			int l = W.length();
			List<Integer>[] list = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				list[i] = new ArrayList<>();
			}

			int[] cnts = new int[26]; // 개수 저장할 배열
			// 알파벳 소문자 a~z는 65~90
			
			int tmp = 0;
			int ansMin = 2147000000;
			int ansMax = 0;
			if (K == 1) {
				sb.append(1).append(" ").append(1).append("\n");
			} else {
				for (int i = 0; i < l; i++) {
					tmp = W.charAt(i) - 'a';
					list[tmp].add(i); // 해당 알파벳의 인덱스 저장
					cnts[tmp]++;
					if (cnts[tmp] == K) {	
						ansMin = Math.min(ansMin, list[tmp].get(K-1) - list[tmp].get(0)+1);
						ansMax = Math.max(ansMax, list[tmp].get(K-1) - list[tmp].get(0)+1);
						cnts[tmp] = K-1;
						list[tmp].remove(0);	
					}
				}
				
				if (ansMax == 0) {
					sb.append(-1).append("\n");
				} else {
					sb.append(ansMin).append(" ").append(ansMax).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
