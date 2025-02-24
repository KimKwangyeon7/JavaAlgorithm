

import java.io.*;
import java.util.*;

public class Main {
	static int N;
    static char[] expr;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expr = br.readLine().toCharArray();

        int numCnt = (N + 1) / 2; 
        dp = new int[numCnt][numCnt][2];

        for (int i = 0; i < numCnt; i++) {
            dp[i][i][0] = dp[i][i][1] = expr[i*2] - '0';
        }

        for (int len = 1; len < numCnt; len++) {
            for (int i = 0; i < numCnt - len; i++) {
                int j = i + len; // 끝 지점
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    char op = expr[k*2+1];
                    
                    if (op == '*') {
                    	int[] results = {
                                calc(dp[i][k][0], dp[k+1][j][0]),
                                calc(dp[i][k][0], dp[k+1][j][1]),
                                calc(dp[i][k][1], dp[k+1][j][0]),
                                calc(dp[i][k][1], dp[k+1][j][1]),
                            };

                        for (int res : results) {
                            dp[i][j][0] = Math.max(dp[i][j][0], res);
                            dp[i][j][1] = Math.min(dp[i][j][1], res);
                        }
                    } else if (op == '+') {
                    	dp[i][j][0] = Math.max(dp[i][j][0], dp[i][k][0] + dp[k+1][j][0]);
                    	dp[i][j][1] = Math.min(dp[i][j][1], dp[i][k][1] + dp[k+1][j][1]);
                    } else {
                    	dp[i][j][0] = Math.max(dp[i][j][0], dp[i][k][0] - dp[k+1][j][1]);
                    	dp[i][j][1] = Math.min(dp[i][j][1], dp[i][k][1] - dp[k+1][j][0]);
                    }
                }
            }
        }
        System.out.println(dp[0][numCnt-1][0]);
    }

    private static int calc(int a, int b) {
        return a * b;
    }
}
