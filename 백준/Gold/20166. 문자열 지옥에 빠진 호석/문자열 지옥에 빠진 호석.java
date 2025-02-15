

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
	static class Dot {
		int x;
		int y;
		int len;
		
		public Dot (int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	static int[] dx = new int[] {-1, 0, 1, 0, -1, 1, -1, 1};
	static int[] dy = new int[] {0, 1, 0, -1, -1, 1, 1, -1};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
        	String str = br.readLine();
        	for (int j = 0; j < M; j++) {
        		board[i][j] = str.charAt(j);
        	}
        }
        List<String> likes = new ArrayList<>();
        for (int i = 0; i < K; i++) {
        	likes.add(br.readLine());
        }
        for (String like: likes) {
        	if (map.containsKey(like)) {
        		int res = map.get(like);
        		sb.append(res).append("\n");
        		continue;
        	}
        	char start = like.charAt(0);
        	int total = like.length();
        	int ans = 0;
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < M; j++) {
            		if (board[i][j] == start) {
            			Queue<Dot> qu = new ArrayDeque<>();
            			qu.offer(new Dot(i, j, 1));
            			
            			while (!qu.isEmpty()) {
            				Dot cur = qu.poll();
            				if (cur.len == total) {
            					ans++;
            					continue;
            				}
            				for (int k = 0; k < 8; k++) {
            					int nx = cur.x + dx[k];
            					int ny = cur.y + dy[k];
            					
            					if (!isBoundary(nx, ny, N, M)) {
            						if (nx < 0) {
            							nx = N-1;
            						} else if (nx >= N) {
            							nx = 0;
            						}
            						if (ny < 0) {
            							ny = M-1;
            						} else if (ny >= M) {
            							ny = 0;
            						}
            					}
            					if (board[nx][ny] != like.charAt(cur.len)) {
            						continue;
            					}
            					qu.offer(new Dot(nx, ny, cur.len+1));
            				}
            			}
            		}
            	}
            }
        	sb.append(ans).append("\n");
        	map.put(like, ans);
        }
        System.out.print(sb);
	}
	private static boolean isBoundary(int x, int y, int N, int M) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
