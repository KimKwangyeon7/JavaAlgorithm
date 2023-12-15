
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 * https://myeongju00.tistory.com/30
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int ans = 0;
		
		int N = Integer.parseInt(br.readLine());
		String order = "";
		int num = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			order = st.nextToken();
			if (order.charAt(1) == 'l') { // all인 경우
				ans = (1 << 21) - 1;
			} else if (order.charAt(0) == 'a') { // add일 때
				num = Integer.parseInt(st.nextToken());
				ans |= (1 << num); // 원소 추가하기
			} else if (order.charAt(0) == 'c') { // check일 때
				num = Integer.parseInt(st.nextToken());
				if ((ans & (1 << num)) == (1 << num)) { // 해당 원소 포함하고 있으면
					sb.append(1).append("\n"); // 1 출력
				} else { // 없으면
					sb.append(0).append("\n"); // 0출력
				}
			} else if (order.charAt(0) == 'r') { // remove일 때
				num = Integer.parseInt(st.nextToken());
				ans &= ~(1 << num); // 원소 삭제하기
			} else if (order.charAt(0) == 'e') { // empty일 때
				ans = 0; 
			} else if (order.charAt(0) == 't') { // toggle일 때
				num = Integer.parseInt(st.nextToken());
				ans ^= (1 << num); // 해당 원소가 있으면 삭제하고, 없으면 추가하기
			}
			//System.out.println(list.toString());
		}
		
		System.out.println(sb);
	}

}
