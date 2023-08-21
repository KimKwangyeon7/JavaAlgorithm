import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW_4013_특이한자석
 * 메모리 17,428 kb 실행시간 112 ms
 * @author kwang
 *
 */
public class Solution {

	static int[][] board;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int K = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			board = new int[4][8]; // 자석 4개의 상태를 저장할 2차원 배열
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[] rotCheck = new boolean[3]; // 1번과 2번, 2번과 3번, 3번과 4번 자석 비교 결과 저장할 배열
			int[] r = new int[2];
			for (int i = 0; i < K; i++) { // 회전정보 입력받기
				st = new StringTokenizer(br.readLine());
				r[0] = Integer.parseInt(st.nextToken());
				r[1] = Integer.parseInt(st.nextToken());
				
				rotCheck = new boolean[3]; // 체크 배열 초기화
				if (board[0][2] != board[1][6]) { // 1번과 2번 자석이 다를 때
					rotCheck[0] = true;
				}
				if (board[1][2] != board[2][6]) { // 2번과 3번 자석이 다를 때
					rotCheck[1] = true;
				}
				if (board[2][2] != board[3][6]) { // 3번과 4번 자석이 다를 때
					rotCheck[2] = true;
				}		
				if (r[0] == 1) {
					rotMagnet(0, r[1]); // 1번 자석 회전
					if (rotCheck[0]) { // 1번 자석과 2번 자석의 극이 다를 때 -> 2번이 회전할 때
						rotMagnet(1, r[1]*(-1)); // 2번 자석 회전
						if (rotCheck[1]) { // 2번과 3번 자석 극이 다를 때 -> 3번이 회전할 때
							rotMagnet(2, r[1]);
							if (rotCheck[2]) { // 3번과 4번 자석 극이 다를 때 -> 4번이 회전할 때
								rotMagnet(3, r[1]*(-1));
							}
						}
					}
				} else if (r[0] == 2) {
					rotMagnet(1, r[1]); // 2번 자석 회전
					if (rotCheck[0]) { // 2번 자석과 1번 자석의 극이 다를 때 -> 1번이 회전할 때
						rotMagnet(0, r[1]*(-1)); // 1번 자석 회전
					}
					if (rotCheck[1]) { // 2번 자석과 3번 자석의 극이 다를 때 -> 3번이 회전할 때
						rotMagnet(2, r[1]*(-1)); // 3번 자석 회전
						if (rotCheck[2]) { // 3번과 4번 자석 극이 다를 때 -> 4번이 회전할 때
							rotMagnet(3, r[1]);
						}
					}
				} else if (r[0] == 3) {
					rotMagnet(2, r[1]); // 3번 자석 회전
					if (rotCheck[1]) { // 3번 자석과 2번 자석의 극이 다를 때 -> 2번이 회전할 때
						rotMagnet(1, r[1]*(-1)); // 2번 자석 회전
						if (rotCheck[0]) { // 2번 자석과 1번 자석의 극이 다를 때 -> 1번이 회전할 때
							rotMagnet(0, r[1]); // 1번 자석 회전
						}
					}
					if (rotCheck[2]) { // 3번 자석과 4번 자석의 극이 다를 때 -> 4번이 회전할 때
						rotMagnet(3, r[1]*(-1)); // 4번 자석 회전
					}
				} else {
					rotMagnet(3, r[1]); // 4번 자석 회전
					if (rotCheck[2]) { // 4번 자석과 3번 자석의 극이 다를 때 -> 3번이 회전할 때
						rotMagnet(2, r[1]*(-1)); // 3번 자석 회전
						if (rotCheck[1]) { // 3번과 2번 자석 극이 다를 때 -> 2번이 회전할 때
							rotMagnet(1, r[1]);
							if (rotCheck[0]) { // 2번과 1번 자석 극이 다를 때 -> 1번이 회전할 때
								rotMagnet(0, r[1]*(-1));
							}
						}
					}	
				}
			}	
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				if (board[i][0] == 1) {
					sum += Math.pow(2, i);
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
	private static void rotMagnet(int magNum, int dir) {
		if (dir == 1) { // 시계 방향 -> 맨뒤를 맨앞으로
			int tmp = board[magNum][7];
			for (int i = 7; i > 0; i--) {
				board[magNum][i] = board[magNum][i-1];
			}
			board[magNum][0] = tmp;
		} else { // 반시계 방향 -> 맨앞을 맨뒤로
			int tmp = board[magNum][0];
			for (int i = 1; i < 8; i++) {
				board[magNum][i-1] = board[magNum][i];
			}
			board[magNum][7] = tmp;
		}		
	}
}
