import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class Main {
	static class Dot implements Comparable<Dot>{
		int x; // 출발지
		int y; // 도착지
		int d; // 다리 길이
		
		public Dot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Dot o) {
			return this.d - o.d;
		}	
	}
	
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static int[] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visit = new boolean[N][M];
		Queue<int[]> qu = new ArrayDeque<>();
		// 섬나누기
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1 && !visit[i][j]) {
					qu.add(new int[] {i, j});
					visit[i][j] = true;
					board[i][j] = cnt;
					
					while (!qu.isEmpty()) {
						int[] tmp = qu.poll();
						for (int k = 0; k < 4; k++) {
							int x = tmp[0] + dx[k];
							int y = tmp[1] + dy[k];
							
							if (!isBoundary(x, y) || board[x][y] == 0 || visit[x][y]) {
								continue;
							}
							
							qu.add(new int[] {x, y});
							visit[x][y] = true;
							board[x][y] = cnt;
						}
					}
					cnt++;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(board[i]));
//		}
		
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		//visit = new boolean[N][M];
		int[][] disMap = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			Arrays.fill(disMap[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] != 0) {
					int x = i;
					int y = j;
					// 상
					while (true) {
						x--;
						if (!isBoundary(x, y) || board[x][y] == board[i][j]) {
							break;
						}
						if (board[x][y] == 0) {
							continue;
						}
						if (i-x-1 > 1) {
							int t = Math.min(disMap[board[i][j]][board[x][y]], disMap[board[x][y]][board[i][j]]);
							disMap[board[i][j]][board[x][y]] = Math.min(t, i-x-1);
							disMap[board[x][y]][board[i][j]] = disMap[board[i][j]][board[x][y]];
							break;
						} else {
							break;
						}
					}
					// 하
					while (true) {
						x++;
						if (!isBoundary(x, y) || board[x][y] == board[i][j]) {
							break;
						}
						if (board[x][y] == 0) {
							continue;
						}
						if (x-i-1 > 1) {
							int t = Math.min(disMap[board[i][j]][board[x][y]], disMap[board[x][y]][board[i][j]]);
							disMap[board[i][j]][board[x][y]] = Math.min(t, x-i-1);
							disMap[board[x][y]][board[i][j]] = disMap[board[i][j]][board[x][y]];
							break;
						} else {
							break;
						}
					}
					// 좌
					while (true) {
						y--;
						if (!isBoundary(x, y) || board[x][y] == board[i][j]) {
							break;
						}
						if (board[x][y] == 0) {
							continue;
						}
						if (j-y-1 > 1) {
							int t = Math.min(disMap[board[i][j]][board[x][y]], disMap[board[x][y]][board[i][j]]);
							disMap[board[i][j]][board[x][y]] = Math.min(t, j-y-1);
							disMap[board[x][y]][board[i][j]] = disMap[board[i][j]][board[x][y]];
							break;
						} else {
							break;
						}
					}
					// 우
					while (true) {
						y++;
						if (!isBoundary(x, y) || board[x][y] == board[i][j]) {
							break;
						}
						if (board[x][y] == 0) {
							continue;
						}
						if (y-j-1 > 1) {
							int t = Math.min(disMap[board[i][j]][board[x][y]], disMap[board[x][y]][board[i][j]]);
							disMap[board[i][j]][board[x][y]] = Math.min(t, y-j-1);
							disMap[board[x][y]][board[i][j]] = disMap[board[i][j]][board[x][y]];
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		
//		for (int i = 1; i < cnt; i++) {
//			System.out.println(Arrays.toString(disMap[i]));
//		}
		
		map = new int[cnt][cnt];
		for (int i = 0; i < cnt; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		
		check = new int[cnt];
		for (int i = 1; i < cnt; i++) {
			check[i] = i;
		}
		
		for (int i = 1; i < cnt; i++) {
			for (int j = i+1; j < cnt; j++) {
				pq.offer(new Dot(i, j, disMap[i][j]));
			}
		}
//		for (Dot d: pq) {
//			System.out.println(d.x + " "+ d.y + " "+ d.d);
//		}
		int c = 0;
		int sum = 0;
		while (!pq.isEmpty()) {
			Dot dot = pq.poll();
			int x = dot.x;
			int y = dot.y;
			
			if (find(x) == find(y) || dot.d == Integer.MAX_VALUE) {
				continue;
			}
			union(x, y);
			c++;
			sum += dot.d;
			if (c == cnt-2) {
				break;
			}
		}
		if (c != cnt-2) {
			System.out.println(-1);
		} else {
			System.out.println(sum);
		}
	}

	private static boolean isBoundary(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	private static int find(int x) {
		if (x == check[x]) {
			return x;
		} else {
			return check[x] = find(check[x]);
		}
	}
	
	private static void union(int x, int y) {
		if (find(y) == find(x)) {
			return;
		}
		check[find(y)] = find(x); 
	}

}