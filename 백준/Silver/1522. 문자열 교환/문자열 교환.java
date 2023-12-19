import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine(); // 문자열 입력 받기
		int len = tmp.length(); // 문자열의 총 길이
		int cnt = 0; // 문자열에서 a 개수 
		for (int i = 0; i < len; i++) { // a 개수 세기
			if (tmp.charAt(i) == 'a') {
				cnt++;
			}
		}
		int min = 2147000000; // b의 최소 개수 
		int bCnt = 0; // b 개수
		int left = 0;
		int right = 0;
		for (int i = 0; i < len; i++) {
			bCnt = 0;
			left = i;
			right = i + cnt - 1;
			while (left < right) {
				if (tmp.charAt(left%len) == 'b') {
					bCnt++;
				}
				if (tmp.charAt(right%len) == 'b') {
					bCnt++;
				}
				left++;
				right--;
			}
            if (left == right) {
				if (tmp.charAt(left%len) == 'b') {
					bCnt++;
				}
			}
			min = Math.min(min, bCnt);
		}
		System.out.println(min);
		
	}

}
