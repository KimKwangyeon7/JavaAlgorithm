import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_2252_줄세우기
 * @author 김광연
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Integer> qu = new ArrayDeque<>(); // 정점을 담을 큐
		List<Integer>[] list = new ArrayList[N+1]; // 각 정점의 간선들 정보를 담을 인접리스트
		for (int i = 0; i < N+1; i++) { // 각각의 배열의 원소를 인스턴스로 지정
			list[i] = new ArrayList<Integer>();
		}
		
		int from = 0;
		int to = 0;
		int[] check = new int[N+1]; // 진입차수를 저장할 배열
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 간선을 리스트에 담음
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			check[to]++; // 진입차수 증가
		}
        
		for (int i = 1; i < N+1; i++) {
			if (check[i] == 0) {
				qu.offer(i);
			}
		}	
		while (!qu.isEmpty()) {
			int tmp = qu.poll(); // 큐에 들어간 순서대로 출력
			sb.append(tmp).append(" "); 
			
			for (int i = 0; i < list[tmp].size(); i++) { // 방금 출력한 정점에서 갈 수 있는 인접노드 찾기
				int node = list[tmp].get(i);
				check[node]--; // 해당 노드의 진입차수 1빼기
				if (check[node] == 0) { // 해당 노드의 진입차수가 0이 되었다면
					qu.offer(node);
				}
			}
		}
		System.out.println(sb);
	}
}