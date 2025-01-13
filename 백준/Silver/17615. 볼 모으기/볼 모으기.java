

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = Integer.MAX_VALUE;
		char[] board = new char[N];
		String str = br.readLine();
		int redCnt = 0;
		for (int i = 0; i < N; i++) {
			board[i] = str.charAt(i);
			if (board[i] == 'R') {
				redCnt++;
			}
		}
		if (redCnt == 0 || redCnt == N) {
			System.out.println(0); 
		} else {
			int cnt = 0;
			int move = 0;
			// 빨간색 볼 먼저 세는 경우
			for (int i = N-1; i >= 0; i--) {
				if (board[i] == 'R') {
					if (i == N-1) {
						cnt++;
					} else if (cnt+i == N-1) {
						cnt++;
					} else {
						cnt++;
						move++;
					}
					if (cnt == redCnt) {
						answer = Math.min(answer, move);
						break;
					}
				}
			}
			
			// 파란색 볼 먼저 세는 경우
			cnt = 0;
			move = 0;
			int blueCnt = N - redCnt;
			for (int i = N-1; i >= 0; i--) {
				if (board[i] == 'B') {
					if (i == N-1) {
						cnt++;
					} else if (cnt+i == N-1) {
						cnt++;
					} else {
						cnt++;
						move++;
					}
					if (move >= answer) {
						break;
					}
					if (cnt == blueCnt) {
						answer = Math.min(answer, move);
						break;
					}
				}
			}
			System.out.println(answer);
		}
	}
}
