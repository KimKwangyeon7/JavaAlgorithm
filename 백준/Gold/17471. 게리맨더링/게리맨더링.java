import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static int N, ans;
	static int[][] board;
	static int[] popul;
	static boolean[] res, another;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N]; // 인접 구역 정보 담을 배열
		popul = new int[N]; // 인구수 담을 배열
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for (int i = 0; i < N; i++) { 
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken()); // 해당 도시의 인접 구역 개수
			for (int j = 0; j < cnt; j++) {
				board[i][Integer.parseInt(st.nextToken())-1] = 1;
			}
		}
		ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N/2; i++) {
			res = new boolean[N]; // 고른 인덱스 체크할 불리언 배열	
			combi(i, 0, 0); // 고를 개수, 현재 고른 개수, 시작 인덱스
		}
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
	private static void combi(int size, int cnt, int start) {
		if (cnt == size) {
			another = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (!res[i]) { // 고르지 않았다면
					another[i] = true; // 상대 체크 배열을 true로
				}
			}
			if (isGary(res) && isGary(another)) { // 둘다 선거구 가능하면
				int sum1 = 0;
				int sum2 = 0;
				for (int i = 0; i < N; i++) {
					if (res[i]) {
						sum1 += popul[i];
					}
					if (another[i]) {
						sum2 += popul[i];
					}
				}
				ans = Math.min(ans, Math.abs(sum1-sum2));
			}
			
			return;
		}
		for (int i = start; i < N; i++) {
			if (!res[i]) {
				res[i] = true;
				combi(size, cnt+1, i+1);
				res[i] = false;
			}
		}
	}
	private static boolean isGary(boolean[] res) { // 해당 선거구가 서로 다 통하는지 확인하는 메서드
		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (res[i]) { // 해당 인덱스가 선거구에 들면 
				idx = i; // 그 인덱스를 저장 -> 시작 인덱스로 하기 위해
				break;
			}
		}
		// BFS
		Queue<Integer> qu = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		qu.offer(idx);
		visit[idx] = true;

		while (!qu.isEmpty()) {
			int tmp = qu.poll();
			for (int i = 0; i < N ; i++) {
				if (board[tmp][i] == 1 && res[i] && !visit[i]) { // 선거구에 속하며 인접노드이고 방문하지 않았다면
					visit[i] = true; // 방문 체크
					qu.offer(i); // 큐에 추가
				}
			}
		}
		// 선택한 애들을 다 순회 가능한 지 확인
		for (int i = 0; i < N; i++) {
			if (res[i] && !visit[i]) { 
				return false; 
			}
		}
		return true;
	}

}
