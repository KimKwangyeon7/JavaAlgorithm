
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author 김광연
 * 문제 자체는 푼 거 같은데 입력을 안받았을 때 알아서 멈춰주는걸 못하겠음
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String S = st.nextToken(); // 개강총회 시작 시간
		String E = st.nextToken(); // 개강총회 끝낸 시간
		String Q = st.nextToken(); // 개강총회 스트리밍 끝낸 시간
		
		int s = toMin(S);
		int e = toMin(E);
		int q = toMin(Q);
		
		HashMap<String, String> hm = new HashMap<>();
		int cnt = 0;		
		String str = br.readLine();
		while (str != null) {
			st = new StringTokenizer(str);
			String time = st.nextToken();
			String id = st.nextToken();
			int t = toMin(time);
			
			if (hm.containsKey(id)) { // id가 이미 존재
				if (t >= e && t <= q) {
					cnt++;
					hm.remove(id);
				}
			} else { // map에 해당 id가 존재 X
				if (t <= s) {
					hm.put(id, time);
				}
			}
			str = br.readLine();
		}
		System.out.println(cnt);		
	}
	private static int toMin(String S) {
		int h = (S.substring(0, 1).charAt(0) - '0') * 10 + (S.substring(1, 2).charAt(0) - '0');
		int m = (S.substring(3, 4).charAt(0) - '0') * 10 + (S.substring(4, 5).charAt(0) - '0');
		return h*60 + m;
	}
}