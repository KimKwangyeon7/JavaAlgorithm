import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Deque<Integer> up = new ArrayDeque<>();
		Deque<Integer> down = new ArrayDeque<>();
		int[] board = new int[2*N+1];
		boolean[] visited = new boolean[N*2+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N*2; i++) {
			int a = Integer.parseInt(st.nextToken());
			board[i] = a;
		}
		for (int i = 1; i <= N; i++) {
			up.offerLast(i);
		}
		for (int i = N+1; i <= N*2; i++) {
			down.offerFirst(i);
		}
		List<Integer> robots = new ArrayList<>();
		int answer = 1;
		int zeroCnt = 0;
		int flag = 0;
		
		while (true) {
			// 컨베이어 벨트 이동
			int p = up.pollLast();
			int q = down.pollFirst();
			up.offerFirst(q);
			down.offerLast(p);
			// 이동 후 로봇이 내리는 위치인 경우 내리기
			if (robots.size() != 0) {	
				for (int i = 0; i < robots.size(); i++) {
					int tmp = robots.get(i);
					if (tmp == up.peekLast()) {
						visited[robots.get(i)] = false;
						robots.remove(i);
						break;
					}
				}
			}
			// 로봇 자체적으로 움직이기
			int remove = -1;
			if (robots.size() != 0) {	
				for (int i = 0; i < robots.size(); i++) {
					int tmp = robots.get(i);
					int next = 0;
					if (tmp == 2*N) {
						next = 1;
					} else{
						next = tmp+1;
					}
					if (!visited[next] && board[next] > 0) {
						visited[tmp] = false;
						visited[next] = true;
						board[next]--;
						if (board[next] == 0) {
							zeroCnt++;
							if (zeroCnt == K) {
								flag = 1;
								break;
							}
						}
						robots.set(i, next);
						if (next == up.peekLast()) {
							remove = i;
							visited[robots.get(remove)] = false;
						}
					}
				}
				if (remove != -1) {
					robots.remove(remove);
				}
			}
			if (flag == 1) {
				break;
			}
			int upFirst = up.peekFirst();
			if (board[upFirst] > 0) {
				board[upFirst]--;
				if (board[upFirst] == 0) {
					zeroCnt++;
					if (zeroCnt == K) {
						break;
					}
				}
				robots.add(upFirst);
				visited[upFirst] = true;
			}
			answer++;
		}
		System.out.println(answer);
	}

}
