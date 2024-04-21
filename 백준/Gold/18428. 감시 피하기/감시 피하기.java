
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	static int N;
	static char[][] board;
	static char[][] copy;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] res;
	static List<Integer> list;
	static List<Integer> teachers;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		list = new ArrayList<>();
		teachers = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = st.nextToken().charAt(0);
				if (board[i][j] == 'X') {
					list.add(N*i+j); // 장애물이 생길 수 있는 칸을 리스트에 담기
				}
				if (board[i][j] == 'T') {
					teachers.add(N*i+j);
				}
			}
		}
		
		
		res = new int[3];
		Arrays.fill(res, -1);
		combi(0, 0, 0);
		System.out.println("NO");
		
	}
	private static void combi(int size, int cnt, int start) {
		if (size == 3) { // 장애물 3개가 다 채워져 있으면
			if (bfs()) { // 감시 피할 수 있는지 확인
				System.out.println("YES");
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(board[i][j] + " ");
//					}
//					System.out.println();
//				}
				System.exit(0);
			}
			return;
		}
		for (int i = start; i < list.size(); i++) {
			res[cnt] = i;
			board[list.get(i)/N][list.get(i)%N] = 'O';
			//System.out.println("장애물 위치: " + list.get(i)/N + " " + list.get(i)%N);
			combi(size+1, cnt+1, i+1);
			board[list.get(i)/N][list.get(i)%N] = 'X';
		}
		
	}
	private static boolean bfs() {
		Queue<int[]> qu = new ArrayDeque<>();
		for (int l: teachers) {
			qu.offer(new int[] {l/N, l%N}); // 선생님들의 위치 좌표 큐에 넣어두기
			//System.out.println("선생님들 위치: " + l/N + " " + l%N);
		}
		
		while (!qu.isEmpty()) {
			int[] tmp = qu.poll();
			for (int k = 0; k < 4; k++) { // 상하좌우 탐색
				int x = tmp[0];
				int y = tmp[1];
				while (true) { // 갈 수 있을 때까지 해당 방향으로 계속 가기
					x += dx[k];
					y += dy[k];
					
					if (!isBoundary(x, y) || board[x][y] == 'O') { // 범위에서 벗어나거사 장애물을 만난 경우 다음 방향으로
						break;
					}
					if (board[x][y] == 'S') { // 학생을 만난 경우 바로 false 반환
						return false;
					}
				}
				
			}
		}
		return true; // 학생을 안만났으니 true 반환
	}
	
	private static boolean isBoundary(int x, int y) { // 범위에서 벗어나는지 판단하는 메서드
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
