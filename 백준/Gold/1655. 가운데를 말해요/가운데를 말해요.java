
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
		StringBuilder sb = new StringBuilder();
		Queue<Integer> quMax = new PriorityQueue<>(Collections.reverseOrder());
		Queue<Integer> quMin = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (quMax.size() == quMin.size()) {
				quMax.offer(num);
			} else {
				quMin.offer(num);
			}
			
			if (!quMax.isEmpty() && !quMin.isEmpty() && quMax.peek() > quMin.peek()) {
				int n1 = quMax.poll();
				int n2 = quMin.poll();
				quMax.offer(n2);
				quMin.offer(n1);
			}
			sb.append(quMax.peek()).append("\n");
		}
		System.out.print(sb.toString().trim());
	}

}
