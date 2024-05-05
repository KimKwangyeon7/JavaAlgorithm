
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

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
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		int[] priority = new int[67];
		priority['*'] = 2;
		priority['/'] = 2;
		priority['+'] = 1;
		priority['-'] = 1;
		priority['('] = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (a == '(') {
				stack.add(a);
			} else if (a == ')') {
				while (stack.peek() != '(') {
					sb.append(stack.pop()); // '('이 나오기 전까지 스택에 있는 모든 연산자 꺼내기
				}
				stack.pop(); // '('도 스택에서 빼기
			} else if (a == '*' || a == '/' || a == '+' || a == '-') { // 연산자인 경우
				while (!stack.isEmpty() && priority[stack.peek()] >= priority[a]) {
	                sb.append(stack.pop());
	            }
	            stack.add(a);
			} else { // 문자인 경우
				sb.append(a);
			}
		}
		while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
	}

}
