import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 보드 한변의 크기 
		int A = Integer.parseInt(br.readLine()); // 사과의 개수
		char[][] board = new char[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], '.');
		}
		int x = 0;
		int y = 0;
		for (int i = 0; i < A; i++) { // 사과 좌표 받기
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			board[x-1][y-1] = 'X'; // 사과를 배열에 X로 표시
		}
		board[0][0] = 'O'; // 뱀의 시작위치 => O로 표시
		int L = Integer.parseInt(br.readLine()); // 방향전환개수
		int[][] move = new int[L][2];
		int t = 0;
		char d;
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			d = st.nextToken().charAt(0);
			move[i][0] = t;
			if (d == 'D') { // D(오른쪽) => 1
				move[i][1] = 1;			
			} else { // L(왼쪽) => -1
				move[i][1] = -1;	
			}
		}
		// 뱀의 시작 위치
		int curx = 0;
		int cury = 0;
		int time = 0;
		int dir = 0; // 시작 방향은 오른쪽
		int idx = 0; // 방향전환 시간 확인할 인덱스
		ArrayDeque<int[]> bam = new ArrayDeque<>();
		bam.offer(new int[] {0, 0});
		
		while (true) {
			// 뱀의 머리부터 현재 위치에서 방향대로 움직이기
			curx += dx[dir];
			cury += dy[dir];
			time++;
			
			if (!isBoundary(curx, cury) || board[curx][cury] == 'O') { // 벽과 만나거나 자기 자신과 만나면 끝
				System.out.println(time); // 시간 출력
				break; // 반복문 탈출
			}
			
			if (board[curx][cury] == 'X') { // 이동한 위치에 사과가 있으면
				bam.offerFirst(new int[] {curx, cury}); // 이동할 위치만 추가 (길이 1 증가)
				board[curx][cury] = 'O'; // 보드에 뱀 표시
			} else { // 사과가 없으면 -> 길이 그대로 -> 이동할 위치 추가 + 마지막 값 제거
				bam.offerFirst(new int[] {curx, cury});
				int[] tmp = bam.pollLast();
				board[curx][cury] = 'O'; // 보드에 뱀 머리 위치 표시
				board[tmp[0]][tmp[1]] = '.';
			}
			
			if (idx < L && time == move[idx][0]) { // 방향 전환할 시간이면
				if (move[idx][1] == 1) { // 우회전
					dir = (dir+1) % 4;
				} else { // 좌회전
					dir = (dir+3) % 4;
				}
				idx++; // 다음 방향 전환 시간과 비교 위해 인덱스 1증가				
			}	
		}
		/*
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		*/
	}
	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
