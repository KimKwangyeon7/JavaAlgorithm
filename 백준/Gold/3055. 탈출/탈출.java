
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int R, C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R][C];
		String tmp;
		int goX = 0;
		int goY = 0;
		int waX = 0;
		int waY = 0;
		
		Queue<int []> water = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'S') { // 고슴도치 시작 좌표 저장
					goX = i;
					goY = j;
				} else if (board[i][j] == '*') { // 물 시작 좌표 큐에 저장, 시간은 0으로 같이 저장
					water.offer(new int[] {i, j, 0});
				}			
			}
		}
		
		
		Queue<int []> gosu = new ArrayDeque<>();
		gosu.offer(new int[] {goX, goY, 0});
		int time = 0;
		int flag = 0;
		while (!gosu.isEmpty()) {
			while (!water.isEmpty()) {
				if (water.peek()[2] == time+1) { // 해당 시간이 지나면 반복문 탈출
					break;
				}
				int[] cur = water.poll();
				for (int k = 0; k < 4; k++) {
					waX = cur[0] + dx[k];
					waY = cur[1] + dy[k];
					
					// 범위에서 벗어남 or 물이 이미 차있음 or 돌이 있음 or 비버의 소굴임 => 패스
					if (!isBoundary(waX, waY) || board[waX][waY] == '*' || board[waX][waY] == 'X' || board[waX][waY] == 'D') {
						continue;
					}
					board[waX][waY] = '*'; // 해당 좌표를 물로 
					water.offer(new int[] {waX, waY, cur[2]+1}); // 시간+1하고 큐에 넣기
				}
			}
			flag = 0 ;
			while (!gosu.isEmpty()) {
				if (gosu.peek()[2] == time+1) { // 해당 시간이 지나면 반복문 탈출
					break;
				}
				int[] cur = gosu.poll();
				for (int k = 0; k < 4; k++) {
					goX = cur[0] + dx[k];
					goY = cur[1] + dy[k];
					
					// 범위에서 벗어남 or 이미 갔던 곳임(고슴도치 존재) or 돌이 있음 or 물이 이미 차있음 => 패스
					if (!isBoundary(goX, goY) || board[goX][goY] == 'S' || board[goX][goY] == 'X' || board[goX][goY] == '*') {
						continue;
					}
					
					if (board[goX][goY] == 'D') { // 비버 소굴에 도착하면
						flag = 1; // 도착을 알리기 위해 설정
						time++; // 시간 1 증가
						break;
					}
					board[goX][goY] = 'S'; // 해당 좌표를 고슴도치로
					gosu.offer(new int[] {goX, goY, cur[2]+1}); // 시간+1하고 큐에 넣기
				}
				if (flag == 1) { // 비버 소굴에 도착했으면
					break;
				}
			}
			if (flag == 1) { // 비버 소굴에 도착했으면
				break;
			} else {
				time++; // 시간 1증가
			}	
		}
		if (flag == 1) {
			System.out.println(time);
		} else {
			System.out.println("KAKTUS");
		}
	}
	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
