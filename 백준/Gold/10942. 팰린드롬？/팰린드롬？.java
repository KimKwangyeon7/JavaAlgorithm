
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
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] board = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	board[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
        	dp[i][i] = 2; 
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	
        	int x = s;
        	int y = e;
        	while (x > 0 && x <= N && y > 0 && y <= N && x <= y) {
        		if (dp[x][y] == 0) {
        			if (board[x] == board[y]) {
        				dp[x][y] = 2;
        				x++;
        				y--;
        				if (x > y) {
        					sb.append(1).append("\n");
        				}
        			} else {
        				for (int p = 0; x-p >= 1 && y+p <= N; p++) {
        					dp[x-p][y+p] = 1;
        				}
        				sb.append(0).append("\n");
        				break;
        			}
        		} else {
        			sb.append(dp[s][e]-1).append("\n");
        			break;
        		}
        	}
        }
//        for (int i = 1; i <= N; i++) {
//        	for (int j = 1; j <= N; j++) {
//        		System.out.print(dp[i][j] + " ");
//        	}
//        	System.out.println();
//        }
        System.out.print(sb.toString().trim());
	}

}
