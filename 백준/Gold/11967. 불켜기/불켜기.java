

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
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {1, -1, 0, 0};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<int[]>[][] list = new ArrayList[N+1][N+1];
        for (int i = 1; i <= N; i++) {
        	for (int j = 1; j <= N; j++) {
        		list[i][j] = new ArrayList<>();
        	}
        }
        int[][] board = new int[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list[x][y].add(new int[] {a, b});
        }
        
        Queue<int[]> qu = new ArrayDeque<>();
        visited[1][1] = true;
        boolean[][] checked = new boolean[N+1][N+1];
        checked[1][1] = true;
        board[1][1] = 1;
        qu.offer(new int[] {1, 1});
        int cnt = 1;
        while (!qu.isEmpty()) {
        	int[] now = qu.poll();
        	int x = now[0];
        	int y = now[1];
        	
        	//System.out.println(x + " " + y);
        	if (!list[x][y].isEmpty()) {
        		//boolean flag = false;
    			for (int[] tmp: list[x][y]) {
    				if (board[tmp[0]][tmp[1]] != 1) {
    					board[tmp[0]][tmp[1]] = 1;
    					cnt++;
    					//flag = true;
    					if (checked[tmp[0]][tmp[1]] && !visited[tmp[0]][tmp[1]]) {
    						visited[tmp[0]][tmp[1]] = true;;
    						qu.offer(new int[] {tmp[0], tmp[1]});
    					}
    				}
    			}
//    			if (flag) {
//    				visited = new boolean[N+1][N+1];
//        			visited[x][y] = true;
//    			}
    		}
        	
        	for (int k = 0; k < 4; k++) {
        		int nx = x + dx[k];
        		int ny = y + dy[k];
        		
        		if (nx < 1 || nx > N || ny < 1 || ny > N || visited[nx][ny]) {
        			continue;
        		}
        		checked[nx][ny] = true;
        		if (board[nx][ny] == 0) {
        			continue;
        		}
        		visited[nx][ny] = true;
        		qu.offer(new int[] {nx, ny});
        	}
        }
        System.out.println(cnt);
	}

}
