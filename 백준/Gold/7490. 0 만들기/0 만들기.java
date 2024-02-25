
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author kwang
 *
 */
public class Main {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) { // 테케 수만큼 반복
			N = Integer.parseInt(br.readLine());
			DFS(1, 1, 0, 1, "1");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static void DFS(int L, int num, int sum, int op, String str) {
		if (L == N) {
			if (sum == 0) {
				return;
			}
//			for (int i = str.length()-1; i >= 0; i--) {
//				// 마지막 연산이 +인 경우와 -인 경우에 따라 sum 구하기
//				if (str.charAt(i) == '+') {
//					sum += num;
//					break;
//				} else if (str.charAt(i) == '-'){
//					sum -= num;
//					break;
//				} else {
//					continue;
//				}
//			}
			sum += num * op;
			// 만들어진 수식의 합이 0이면 출력 결과에 추가
			if (sum == 0) {
				sb.append(str).append("\n");
			}
			return;
		}
		// 수식이 ""인 경우
		DFS(L+1, num*10 + L+1, sum, op, str+" "+Integer.toString(L+1));
		
		// 수식이 +인 경우
		DFS(L+1, L+1, num*op + sum, 1, str+"+"+Integer.toString(L+1));
		
		// 수식이 -인 경우
		DFS(L+1, L+1, num*op + sum, -1, str+"-"+Integer.toString(L+1));
		
	}
}
