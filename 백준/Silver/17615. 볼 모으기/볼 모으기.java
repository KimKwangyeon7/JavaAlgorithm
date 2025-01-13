

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
		int blueCnt = N - redCnt;
		if (redCnt == 0 || redCnt == N) {
			System.out.println(0); 
		} else {
			int flag = 0;
			char color = board[N-1];
			int cnt = 1;
			for (int i = N-2; i >= 0; i--) {
				if (board[i] == color) {
					cnt++;
				} else {
					break;
				}
			}
			if (color == 'R') {
				if (cnt == redCnt) {
					flag = 1;
					answer = 0;
				} else if (redCnt-cnt < blueCnt) {
					answer = redCnt - cnt;
				} else {
					answer = blueCnt;
				}
			} else {
				if (cnt == blueCnt) {
					flag = 1;
					answer = 0;
				} else if (blueCnt-cnt < redCnt) {
					answer = blueCnt - cnt;
				} else {
					answer = blueCnt;
				}
			}
			
			if (flag == 1) {
				System.out.println(answer);
			} else {
				color = board[0];
				cnt = 1;
				for (int i = 1; i < N; i++) {
					if (board[i] == color) {
						cnt++;
					} else {
						break;
					}
				}
				if (color == 'R') {
					if (cnt == redCnt) {
						answer = 0;
					} else if (redCnt-cnt < blueCnt) {
						answer = Math.min(answer, redCnt - cnt);
					} else {
						answer = Math.min(answer, blueCnt);
					}
				} else {
					if (cnt == blueCnt) {
						answer = 0;
					} else if (blueCnt-cnt < redCnt) {
						answer = Math.min(answer, blueCnt - cnt);
					} else {
						answer = Math.min(answer, blueCnt);
					}
				}
				System.out.println(answer);
			}
		}
	}
}
