
/**
 * @author kwang
 *
 */

import java.io.*;
import java.util.*;


public class Main {

	/**
	 * @param args
	 */
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] board = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
        	Arrays.fill(board[i], INF);
        	board[i][i] = 0;
        }
        for (int i = 0;i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	board[a][b] = Math.min(board[a][b], c);
        }
        
        for (int k = 1; k <= n; k++) {
        	for (int i = 1; i <= n; i++) {
        		for (int j = 1; j <= n; j++) {
        			if (board[i][k] != INF && board[k][j] != INF) {
        				board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
        			}
        		}
        	}
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
        	for (int j = 1; j <= n; j++) {
                if (board[i][j] == INF) {
        			board[i][j] = 0;
        		}
        		if (j != n) {
        			sb.append(board[i][j]).append(" ");
        		} else {
        			sb.append(board[i][j]);
        		}
        	}
        	if (i != n) {
        		sb.append("\n");
        	}
        }
        System.out.println(sb);
	}

}
