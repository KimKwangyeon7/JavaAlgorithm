

/**
 * @author kwang
 *
 */
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] board = new int[N];
		Stack<Integer> right = new Stack<>();
		
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			board[i] = tmp;
		}
		
		long ans = 0L;
		for (int i = 0; i < N; i++) {
			while (!right.isEmpty()) {
				if (board[i] >= right.peek()) {
					right.pop();
				} else {
					break;
				}
			}
			ans += right.size();
			right.push(board[i]);
		}
		System.out.println(ans);
	}
	
}
