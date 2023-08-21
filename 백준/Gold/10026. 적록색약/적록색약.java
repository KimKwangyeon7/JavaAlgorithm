import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BJ_10026_적록색약
 * 
 * 아이디어
 * @author 김광연
 *
 */
public class Main {
	
	// 상하좌우 탐색 위한 배열
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] dis;
	static int cnt, N;
	static Queue<int[]> qu;
	static boolean[][] visit;
	static char[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>(); // 초록색인 좌표 저장할 리스트
		board = new char[N][N];
		for (int i = 0; i < N; i++) { // 전체 구역 입력 받기
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'G') { // 초록인 경우 리스트에 좌표 저장
					list.add(new int[] {i, j});
				}
			}
		}
		qu = new ArrayDeque<>(); // 좌표 이동 순서를 담을 배열(인접 좌표 우선으로)
		cnt = 0; // 구역 개수 
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					qu.add(new int[] {i, j});
					bfs();
					cnt++;
				}
			}
		}
		sb.append(cnt).append(" "); // 일반적인 사람이 봤을 때의 구역의 개수 저장
		
		for (int[] tmp: list) { // 초록색인 칸을 빨강으로 바꾸기
			board[tmp[0]][tmp[1]] = 'R';
		}
		
		cnt = 0; // 구역 개수 다시 초기화
		visit = new boolean[N][N]; // 방문체크도 초기화
		qu.clear(); // 큐도 초기화
		// 바꾼 상태에서 다시 구역 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					qu.add(new int[] {i, j});
					bfs();
					cnt++;
				}
			}
		}
		sb.append(cnt); // 적록색약이 본 구역 수 저장
		System.out.println(sb);
	}
	static void bfs() {
		while (!qu.isEmpty()) { // 더이상 같은 색깔이 상하좌우에 없을 때까지 반복
			int[] tmp = qu.poll();
			for (int k = 0; k < 4; k++) {
				int x = tmp[0] + dx[k];
				int y = tmp[1] + dy[k];
				
				if (!isBoundary(x, y) || visit[x][y]) { // 범위에서 벗어나거나 이미 방문한 곳이면 패스
					continue;
				}
				if (board[x][y] == board[tmp[0]][tmp[1]]) { // 인접한 좌표와 같은 색깔인 경우
					visit[x][y] = true;
					qu.add(new int[] {x, y});
				}	
			}
		}
	}
	static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
