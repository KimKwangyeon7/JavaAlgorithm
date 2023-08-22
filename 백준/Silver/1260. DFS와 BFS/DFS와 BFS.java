import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	static int N;
	static int[][] board;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점번호
		
		board = new int[N][N]; // 정점 간의 연결 관계를 표시할 배열 생성
		for (int i = 0; i < M; i++) { // 간선 정보 저장하기
			st = new StringTokenizer(br.readLine()); 
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			board[from-1][to-1] = 1; // 양방향이므로
			board[to-1][from-1] = 1;
		}
		visit = new boolean[N];
		sb.append(V).append(" ");
		visit[V-1] = true;
		dfs(0, V-1);
		sb.append("\n");
		visit = new boolean[N];
		bfs(V-1);
		System.out.println(sb);
	}
	static void dfs(int size, int start) {
		if (size == N) {
			sb.append("\n");
		} else {
			for (int i = 0; i < N; i++) {
				if (board[start][i] == 1 && !visit[i]) { // 간선이 연결되어 있고 들린적 없는 정점
					visit[i] = true;
					sb.append(i+1).append(" ");
					dfs(size+1, i);
				}
			}
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> qu = new LinkedList<>();
		qu.add(start);
		visit[start] = true;
		sb.append(start+1).append(" ");
		while (!qu.isEmpty()) {
			int tmp = qu.poll();
			for (int i = 0; i < N; i++) {
				if (board[tmp][i] == 0 || visit[i]) {
					continue;
				}
				visit[i] = true;
				qu.add(i);
				sb.append(i+1).append(" ");
			}
		}
	}
}
