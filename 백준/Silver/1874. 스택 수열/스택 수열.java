


import java.io.*;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] board = new int[N];
		for (int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(br.readLine());
		}
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int num = 1;
		int flag = 0;
		for (int i = 0; i < N ; i++) {
			while (true) {
				if (!stack.isEmpty()) {
					if (stack.peek() == board[i]) {
						stack.pop();
						sb.append("-").append("\n");
						break;
					}
				}
				
				if (!stack.isEmpty() && num > N) {
					flag = 1;
					i = N;
					break;
				}
				
				stack.push(num++);
				sb.append("+").append("\n");
			}
		}
		if (flag == 1) {
			System.out.println("NO");
		} else{
			System.out.println(sb);
		}
	}

}
