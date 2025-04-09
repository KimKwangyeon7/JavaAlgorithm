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
	static final int MOD = 1000000003;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        
 
    	int[][] dp = new int[N+1][K+1];
    	
    	// 첫번째 색 고르지 않는 경우
    	for (int i = 1; i <= N; i++) {
    		dp[i][1] = i;
    		dp[i][0] = 1;
    	}
    	for (int i = 2; i <= N; i++) {
    		for (int j = 2; j <= K; j++) {
    			dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD;
    		}
    	}
    	int res = (dp[N-1][K] + dp[N-3][K-1]) % MOD;
    	System.out.println(res);
//    	for (int i = 1; i <= N; i++) {
//    		for (int j = 1; j <= N; j++) {
//    			System.out.print(dp[i][j] + " ");
//    		}
//    		System.out.println();
//    	}
	}

}
