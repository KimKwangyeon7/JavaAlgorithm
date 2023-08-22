import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] board;
	static int[][] student;
	static StringTokenizer st;
	static BufferedReader br;
	static int N;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 스위치의 개수
		board = new int[N+1]; // 1~N 스위치 상태 저장하는 배열
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		student = new int[M][2]; // 학생 저장하는 배열
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			student[i][0] = Integer.parseInt(st.nextToken());
			student[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int[] tmp: student) { // 학생들의 상태 바꾸기
			if (tmp[0] == 1) { // 남학생일 때
				for (int i = 1; i < N+1; i++) {
					if (i % tmp[1] == 0) { // 인덱스가 0부서 시작했으므로 1증가한 값이 받은 수의 배수일 때
						change(i); // 해당 번호 스위치의 상태 바꾸기
					}
				}
			} else { // 여학생일 때
				change(tmp[1]); // 받은 수와 같은 번호의 스위치는 무조건 상태 변경
				int t = 1; // 양 옆 확인 범위
				// 양 옆에 수가 같으면 그 범위를 점점 넓혀 나감 
				while (isBoundary(tmp[1] - t) && isBoundary(tmp[1] + t)) {
					if (board[tmp[1] - t] == board[tmp[1] + t]) { // 양 옆 같을 떄
						change(tmp[1] - t); // 두 스위치의 상태 변경
						change(tmp[1] + t);
						t++; // 범위 증가
						continue;
					} else { // 다를 때
						break;
					}
				}
				

			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(board[i+1] + " ");
			if (((i+1)%20) == 0 && N != 0) { // 20으로 나눠지면 -> 20의 배수
				System.out.println(); // 다음 줄로
			}
		}

	}
	
	static void change(int idx) {
		if (board[idx] == 0) {
			board[idx] = 1;
		} else {
			board[idx] = 0;
		}
	}
	static boolean isBoundary(int x) {
		return x >= 1 && x < N+1;
	}
	
}


