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
			boolean[] visit1 = new boolean[100001];
			boolean[] visit2 = new boolean[100001];
			boolean[] visit3 = new boolean[100001];
			while(!qu.isEmpty()) { // 큐가 비기 전까지 반복
				int[] tmp = qu.poll();
				// 수빈이가 갈 수 있는 3가지 방법
				int a = tmp[0]+1;
				int b = tmp[0]-1;
				int c = tmp[0]*2;
				
				if (isBoundary(a) && !visit1[a]) {
					if (a == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}	
					visit1[a] = true;
					qu.add(new int[] {a, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}
				
				if (isBoundary(b) && !visit2[b]) {
					if (b == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}
					visit2[b] = true;
					qu.add(new int[] {b, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}
				
				if (isBoundary(c) && !visit3[c]) {
					if (c == K) { // 동생과 만나면
						System.out.println(tmp[1]+1);
						qu.clear(); // 탈출 조건
						break;
					}
					visit3[c] = true;
					qu.add(new int[] {c, tmp[1]+1}); // 새로운 위치와 시간을 1더한 후 큐에 저장
				}				
			}
		}
		
	}
	static boolean isBoundary(int x) {
		return x >= 0 && x <= 100000;
	}
}
