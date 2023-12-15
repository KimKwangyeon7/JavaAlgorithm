
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		String order = "";
		int num = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			order = st.nextToken();
			if (order.charAt(1) == 'l') { // all인 경우
				list.clear();
				for (int j = 1; j <= 20; j++) {
					list.add(j);
				}
			} else if (order.charAt(0) == 'a') { // add일 때
				num = Integer.parseInt(st.nextToken());
				if (!list.contains(num)) {
					list.add(num);
				}
			} else if (order.charAt(0) == 'c') { // check일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if (order.charAt(0) == 'r') { // remove일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(new Integer(num));
				}
			} else if (order.charAt(0) == 'e') { // empty일 때
				list.clear();
			} else if (order.charAt(0) == 't') { // toggle일 때
				num = Integer.parseInt(st.nextToken());
				if (list.contains(num)) {
					list.remove(new Integer(num));
				} else {
					list.add(num);
				}
			}
			//System.out.println(list.toString());
		}
		
		System.out.println(sb);
	}

}
