

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] board = new int[N];
		int[] left = new int[N];
		int[] right = new int[N];
		Stack<int[]> leftStack = new Stack<>();
		Stack<int[]> rightStack = new Stack<>();
		int[] near = new int[N]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				left[i] = 0;
				leftStack.push(new int[] {i, board[i]});
				
				right[N-1-i] = 0;
				rightStack.push(new int[] {N-1-i, board[N-1-i]});
			} else {
				if (leftStack.isEmpty()) {
					left[i] = 0;
					leftStack.push(new int[] {i, board[i]});
				} else {
					while (!leftStack.isEmpty()) {
						if (leftStack.peek()[1] <= board[i]) {
							leftStack.pop();
						} else {
							break;
						}
					}
					left[i] = leftStack.size();
					if (left[i] != 0) {
						if (near[i] == 0) {
							near[i] = leftStack.peek()[0] - i;
						} else {
							if (near[i] >= i-leftStack.peek()[0]) {
								near[i] = leftStack.peek()[0]-i;
							}
						}
					}
					leftStack.push(new int[] {i, board[i]});
				}
				
				if (rightStack.isEmpty()) {
					right[N-1-i] = 0;
					rightStack.push(new int[] {N-1-i, board[N-1-i]});
				} else {
					while (!rightStack.isEmpty()) {
						if (rightStack.peek()[1] <= board[N-1-i]) {
							rightStack.pop();
						} else {
							break;
						}
					}
					right[N-1-i] = rightStack.size();
					if (right[N-1-i] != 0) {
						if (near[N-1-i] == 0) {
							near[N-1-i] = rightStack.peek()[0] - (N-1-i);
						} else {
							if (Math.abs(near[N-1-i]) > rightStack.peek()[0] - (N-1-i)) {
								near[N-1-i] = rightStack.peek()[0] - (N-1-i);
							}
						}
					}
					rightStack.push(new int[] {N-1-i, board[N-1-i]});
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int sum = left[i] + right[i];
			if (sum == 0) {
				sb.append(0).append("\n");
			} else {
				int close = i + near[i] + 1;
				sb.append(sum).append(" ").append(close).append("\n");
			}
		}
		System.out.println(sb);
	}

}
