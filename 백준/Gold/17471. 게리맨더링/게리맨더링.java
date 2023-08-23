import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연 
 *
 */
public class Main {
	
	static List<Integer>[] list;
	static int[] res, popul, tmp, r;
	static int N, total, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		popul = new int[N]; // 각 구역 인구 수 담을 배열
		
		total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
			total += popul[i];
		}
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
			for (int j = 0; j < cnt; j++) { // 간선 정보 입력 받기
				int tmp = Integer.parseInt(st.nextToken()) - 1; // 인덱스를 0부터 시작했기 때문에 1 빼고 넣어주기
				list[i].add(tmp);
			}
		}
		r = new int[N];
		ans = 2147000000;
		for (int i = 1; i <= N/2; i++) {
			part(i,0, 0);
		}
		if (ans == 2147000000) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
	
	static void part(int tsize, int size, int start) {
		if (size == tsize) {
			res = new int[size];
			for (int i = 0; i < size; i++) {
				res[i] = r[i];
			}
			tmp = new int[N-size];
			
			int flag, idx = 0;
			for (int i = 0; i < N; i++) { // res의 차집합 구하기
				flag = 0;
				for (int j = 0; j < size; j++) {
					if (res[j] == i) {
						flag = 1;
						break;
					}		
				}
				if (flag == 0) {
					tmp[idx++] = i;
				}
			}

			if (isRoute(res) && isRoute(tmp)) {
				// 인구수 차이 구하기
				int sum = 0; 
				for (int k = 0; k < size; k++) {
					sum += popul[res[k]];
				}
				ans = Math.min(ans, Math.abs(total-sum-sum));
			}
			return;
		}
		for (int i = start; i < N; i++) {
			r[size] = i;
			part(tsize, size+1, i+1); // 골랐을 때
		}
	}
	static boolean isRoute(int[] res) {
		boolean[] check = new boolean[N];
		for (int i = 0; i < res.length; i++) {
			check[res[i]] = true;
		}
		Queue<Integer> qu = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		qu.offer(res[0]);
		visit[res[0]] = true;
		
		while (!qu.isEmpty()) {
			int t = qu.poll();
			for (int n: list[t]) {
				if (check[n] && !visit[n]) { // 인접 정점이면서 방문 X
					visit[n] = true;
					qu.offer(n);
				}
			}
		}
		for (int i = 0; i < res.length; i++) {
			if (!visit[res[i]]) { // res에 속한 모든 정점을 방문하지 못했으면 false 반환
				return false;
			}
		}
		return true; // 모두 방문했으면 true 반환
	}
}
