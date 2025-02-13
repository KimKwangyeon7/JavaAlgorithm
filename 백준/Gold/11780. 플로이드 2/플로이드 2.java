

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
        List<Integer>[][] path = new ArrayList[n + 1][n + 1]; // 경로 저장
        for (int i = 1; i <= n; i++) {
        	Arrays.fill(board[i], INF);
        	for (int j = 1; j <= n; j++) {
                path[i][j] = new ArrayList<>(); // 경로 리스트 초기화
            }
        	board[i][i] = 0;
        }
        for (int i = 0;i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	if (board[a][b] > c) {
                board[a][b] = c;
                path[a][b].clear();
                path[a][b].add(a);
                path[a][b].add(b);
            }
        }
        
        for (int k = 1; k <= n; k++) {
        	for (int i = 1; i <= n; i++) {
        		for (int j = 1; j <= n; j++) {
        			if (board[i][k] != INF && board[k][j] != INF) {
                        if (board[i][j] > board[i][k] + board[k][j]) {
                            board[i][j] = board[i][k] + board[k][j];
                            // 기존 경로 삭제 후 새로운 경로 갱신
                            path[i][j].clear();
                            path[i][j].addAll(path[i][k]); // i → k 경로 추가
                            path[i][j].remove(path[i][j].size() - 1); // 중복 제거
                            path[i][j].addAll(path[k][j]); // k → j 경로 추가
                        }
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
        
        // **2. 최단 경로 출력**
        StringBuilder sb2 = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || board[i][j] == INF) {
                    sb2.append(0).append("\n");
                } else {
                    sb2.append(path[i][j].size()).append(" ");
                    for (int city : path[i][j]) {
                        sb2.append(city).append(" ");
                    }
                    sb2.append("\n");
                }
            }
        }
        System.out.print(sb2);
	}
}
