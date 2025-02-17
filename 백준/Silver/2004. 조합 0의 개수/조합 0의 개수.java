
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long K = N-M;
        
        long totalFive = getFive(N) - getFive(M) - getFive(K);
        long totalTwo = getTwo(N) - getTwo(M) - getTwo(K);
        System.out.println(Math.min(totalFive, totalTwo));
	}

	private static long getTwo(long num) {
		long cnt = 0;
		for (long i = 2; i <= num; i*=2) {
			cnt += num / i;
		}
		return cnt;
	}
	private static long getFive(long num) {
		long cnt = 0;
		for (long i = 5; i <= num; i*=5) {
			cnt += num / i;
		}
		return cnt;
	}
}
