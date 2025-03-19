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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] loc = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            loc[arr[i]] = i;
        }
        
        int maxLIS = 1;
        int curLen = 1;

        for (int i = 2; i <= N; i++) { 
            if (loc[i] > loc[i - 1]) {  
                curLen++;
            } else {
                curLen = 1;
            }
            maxLIS = Math.max(maxLIS, curLen);
        }
        System.out.println(N - maxLIS);

	}
}
