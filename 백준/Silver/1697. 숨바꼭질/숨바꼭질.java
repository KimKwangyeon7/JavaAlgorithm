import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_1697_숨바꼭질
 * 20,986 kb 시간 132 ms
 * 아이디어
 * BFS를 활용하는 문제 
 * 수빈이와 동생의 위치를 가지고 우선 경우를 나눈 후 수빈이가 갈 수 있는 3가지 경우를 가지고 BFS 탐색
 * 최소 시간을 구하는 거기 때문에 동생과 만나는 즉시 출력하고 반복문을 탈출
 * 이때, 방문체크를 안하자 메모리 초과가 됨 
 * => 사이즈 100001짜리 boolean 배열 3개를 만들어서 활용하면 체크 없이 그냥 여러번 배열 생성하는 것이 메모리를 더 잡아먹을 수 있다는 것을 깨달음
 * @author 김광연
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		if (N >= K) { // 수빈이의 위치가 동생의 위치와 같거나 오른쪽
			System.out.println(N-K);
		} else { // 수빈이의 위치가 동생보다 왼쪽
			Queue<int[]> qu = new LinkedList<>(); // bfs에 사용할 큐(수빈이가 이동할 수 있는 경우를 큐에 담음)
			qu.add(new int[] {N,0});
			// 수빈이가 갈 수 있는 경우들 각각을 체크할 방문 배열
			boolean[] visit = new boolean[100001];
			
			while(!qu.isEmpty()) { // 큐가 비기 전까지 반복
				int[] tmp = qu.poll();
				// 수빈이가 갈 수 있는 3가지 방법
				int a = tmp[0]+1;
				int b = tmp[0]-1;
				int c = tmp[0]*2;
				
				if (isBoundary(a) && !visit[a]) {
					if (a == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}	
					visit[a] = true;
					qu.add(new int[] {a, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}
				
				if (isBoundary(b) && !visit[b]) {
					if (b == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}
					visit[b] = true;
					qu.add(new int[] {b, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}
				
				if (isBoundary(c) && !visit[c]) {
					if (c == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}
					visit[c] = true;
					qu.add(new int[] {c, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}				
			}
		}
		
	}
	static boolean isBoundary(int x) { // 범위 내에 있는지 확인하는 메서드
		return x >= 0 && x <= 100000;
	}
}
