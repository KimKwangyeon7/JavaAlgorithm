

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> group = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			group.put(a, i);
			Map<String, String> map = new TreeMap<>();
			int cnt = Integer.parseInt(br.readLine());
			for (int k = 0; k < cnt; k++) {
				map.put(br.readLine(), a);
			}
			list.add(map);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			int type = Integer.parseInt(br.readLine());
			if (type == 0) {
				for (String name: list.get(group.get(tmp)).keySet()) {
					sb.append(name).append("\n");
				}
			} else {
				for (Map<String, String> li: list) {
					if (li.containsKey(tmp)) {
						sb.append(li.get(tmp)).append("\n");
						break;
					}
				}
			}
		}
		System.out.print(sb);
	}

}
