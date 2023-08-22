import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static boolean[] visit;
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n];
		for (int i =  0; i < n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		int a = 0;
		int b = 0;
		for (int i = 0; i < m; i++) { // 친구 관계 받기
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b); // 무향 그래프와 같으므로 반대도 리스트에 넣어줌
			list[b].add(a);
		}
		visit = new boolean[n];
		int flag = 0;
		for (int i = 0; i < n; i++) { // 시작점 정하기
			visit[i] = true;
			if (dfs(1, i)) {
				System.out.println(1);
				flag = 1;
				break;
			}
			visit[i] = false;
		}
		if (flag == 0) {
			System.out.println(0);
		}
	}
	static boolean dfs(int size, int node) {
		if (size == 5) {
			return true;
		} else {
			visit[node] = true;
			for (int n: list[node]) {
				if (!visit[n] && dfs(size+1, n)) {
					return true;
					}
				}
			visit[node] = false;
			}
		return false;
	}
}