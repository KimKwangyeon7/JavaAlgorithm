

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		for (int i = 0; i < N; i++) { // 스카이라인 정보 입력 받기
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			h[i] = Integer.parseInt(st.nextToken());
		}
		int ans = 0; // 건물 개수
		Stack<Integer> s = new Stack<>();
		for (int y: h) { // 스카이라인 y 값들 모두 체크
			if (y == 0) { // 높이가 0일 경우 건물이 연결이 안되므로 스택 비우기
			 ans += s.size();
			 s.clear();
			}
			else if (!s.isEmpty()) { // 높이가 0이 아니고 스택이 비어있지 않으면
				int tmp = s.peek(); // 가장 최근 높이 값
				if (y > tmp) { // 높이가 더 높아지면
					s.push(y);
				} else { // 높이가 더 낮으면
					while (!s.isEmpty() && s.peek() > y) { 
						s.pop();
						ans++;
					}
					if (!s.isEmpty() && s.peek() < y) {
						s.push(y);
					}
					if (s.isEmpty()) {
						s.push(y);
					}
				}
			} else { // 스택이 비어있으면 바로 푸쉬
				s.push(y);
			}
		}
		ans += s.size();
		s.clear();
		System.out.println(ans);
	}

}
