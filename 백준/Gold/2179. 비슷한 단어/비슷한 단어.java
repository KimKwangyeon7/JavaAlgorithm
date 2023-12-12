
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김광연
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 문자열의 개수
		String[] words = new String[N]; // 문자열들을 담을 배열
		
		for (int i = 0; i < N; i++) { // 입력 저장
			words[i] = br.readLine();
		}
		
		int max = -1;
		String S = "";
		String T = "";
		for (int i = 0; i < N; i++) {
			if (words[i].length() < max) {
				continue;
			}
			for (int j = i+1; j < N; j++) {
				if (words[j].length() < max) {
					continue;
				}
				if (prefix(words[i], words[j]) > max) { // 두문자열의 공통 접두사 길이가 더 크면
					max = prefix(words[i], words[j]); // max, S, T 값 update
					S = words[i]; 
					T = words[j];
				}
			}
		}
		
		System.out.println(S);
		System.out.println(T);
		
	}
	
	static int prefix(String a, String b) { // 두 문자열의 공통 접두사 길이 구하는 메서드
		int lenA = a.length(); // 문자열 a의 길이
		int lenB = b.length(); // 문자열 b의 길이
		int cnt = 0; // 공통 접두사 길이 변수
		
		for (int i = 0; i < Math.min(lenA, lenB); i++) { // 두 문자열 중 더 길이가 짧은 길이까지 비교
			if (a.charAt(i) != b.charAt(i)) { // 해당 위치의 문자가 다르면 탈출
				break;
			}
			cnt++; // 같으면 cnt 1 증가
		}
		return cnt;
	}
}
