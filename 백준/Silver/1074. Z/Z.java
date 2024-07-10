
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int N, r, c;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int total = (int)Math.pow(2, N);
		dac(r, c, total);
		System.out.println(ans);
	}
	private static void dac(int row, int col, int size) {
		
		if (size < 2) {
			return;
		}
		if (row < size/2 && col < size/2) {
			dac(row, col, size/2);
		} else if (row < size/2 && col >= size/2) {
			ans += size/2 * size/2;
			dac(row, col - size/2, size/2);
		} else if (row >= size/2 && col < size/2) {
			ans += (size/2 * size/2) * 2;
			dac(row - size/2, col, size/2);
		} else {
			ans += (size/2 * size/2) * 3;
			dac(row - size/2, col - size/2, size/2);
		}
	}
}
