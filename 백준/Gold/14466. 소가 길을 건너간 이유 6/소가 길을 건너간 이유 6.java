
import java.io.*;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static class Cow{
		int x;
		int y;
		public Cow(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {1, -1, 0, 0};
	static List<int[]>[][] board;
	static List<Cow> cowList;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        
        board = new ArrayList[N+1][N+1];
        for (int i = 1; i <= N; i++) {
        	for (int j = 1; j <= N; j++) {
        		board[i][j] = new ArrayList<>();
        	}
        }
        for (int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            board[x1][y1].add(new int[] {x2, y2});
            board[x2][y2].add(new int[] {x1, y1});
        }
        
        cowList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cowList.add(new Cow(x, y));
        }
        int ans = 0;
        int len = cowList.size();
        for (int i = 0; i < len-1; i++) {
        	for (int j = i+1; j < len; j++){
        		if (bfs(i, j, N)) {
        			ans++;
        		}
       		}
        }
        System.out.println(ans);
	}
	private static boolean bfs(int start, int end, int N) {
		boolean[][] visited = new boolean[N+1][N+1];
		Queue<Cow> qu = new ArrayDeque<>();
		qu.offer(cowList.get(start));
		visited[cowList.get(start).x][cowList.get(start).y] = true;
		
		while (!qu.isEmpty()) {
			Cow now = qu.poll();
			
			int x = now.x;
			int y = now.y;
			//System.out.println(x + " " + y);
			
			if (x == cowList.get(end).x && y == cowList.get(end).y) {
				return false;
			}
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if (!isBoundary(nx, ny, N) || visited[nx][ny]) {
					continue;
				}
				if (!board[x][y].isEmpty()) {
					List<int[]> list = board[x][y];
					//System.out.println(x + " " + y + " " + list.size());
					boolean flag = false;
					for (int[] tmp: list) {
						//System.out.println(x + " " + y + " " + tmp[0] + " " + tmp[1]);
						if (nx == tmp[0] && ny == tmp[1]) {
							flag = true;
							break;
						}
					}
					if (flag) {
						continue;
					}
				} 
				visited[nx][ny] = true;
				qu.offer(new Cow(nx, ny));
			}
		}
		return true;
	}
	private static boolean isBoundary(int x, int y, int N) {
		return x >= 1 && x <= N && y >= 1 && y <= N;
	}

}
