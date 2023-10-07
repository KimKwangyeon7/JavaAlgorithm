
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * BJ_14501_퇴사_김광연
 * 메모리
 * 아이디어
 * 그리디 문제. 전에 풀어봤던 문제라 어떻게 풀지 기억이 나 풀기 수월했음
 * 그리디가 가능한 전제 조건이 정확히 기억이 안나서 개념만 조금 정리함
 */
public class Main {

	static int[] T;
	static int[] P;
	static int ans, N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 0; // 최대 수익을 구하기 위한 변수
		DFS(0, 0);
		System.out.println(ans);
		
	}

	private static void DFS(int cnt, int sum) {
		if (cnt == N) { // 총 일수와 같아지면
			ans = Math.max(ans, sum);
			return;
		}
		if (cnt + T[cnt] < N+1) { // 해당 날짜의 상담을 했을 때 끝나는 날이 N보다 크면 그 상담은 못하므로 
			DFS(cnt+T[cnt], sum+P[cnt]);
		}
		DFS(cnt+1, sum);
	}

}
