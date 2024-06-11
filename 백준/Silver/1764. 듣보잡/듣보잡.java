
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//List<String> list = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		Queue<String> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if (set.contains(str)) {
				cnt++;
				pq.offer(str);
			}
		}
		System.out.println(cnt);
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		System.out.println(sb);
	}

}
