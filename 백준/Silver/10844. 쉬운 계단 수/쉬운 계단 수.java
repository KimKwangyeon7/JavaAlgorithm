
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static final int MOD = 1000000000;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][10];
        
        for (int i = 0; i <= 9; i++) {
        	dp[1][i] = 1;
        }
        int res = 0;
        for (int i = 2; i <= N; i++) {
        	for (int j = 0; j <= 9; j++) {
        		if (j == 9) {
        			dp[i][j] = dp[i-1][j-1] % MOD;
        		} else if (j == 0) {
        			dp[i][j] = dp[i-1][j+1] % MOD;
        		} else {
        			dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
        		}
        		if (i == N && j != 0) {
        			res = (res + dp[i][j]) % MOD;
        		}
        	}
        }
        System.out.println((N == 1)? 9: res);
	}

}
