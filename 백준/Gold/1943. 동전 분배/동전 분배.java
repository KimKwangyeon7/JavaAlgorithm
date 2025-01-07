

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int i = 0; i < 3; i++) {
			int N = Integer.parseInt(br.readLine());
			Map<Integer, Integer> map = new HashMap<>();
			List<Integer> list = new ArrayList<>();
			int sum = 0;
			boolean[] dis = new boolean[100001];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int coin = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				map.put(coin, cnt);
				list.add(coin);
				sum += coin * cnt;
			}
			if (sum % 2 != 0) {
				sb.append(0).append("\n");
				continue;
			}
			int target = sum / 2;
//			if (dis[target]) {
//				sb.append(1).append("\n");
//				continue;
//			}
			
			boolean flag = false;
			for (int k = 0; k < list.size(); k++) {
				int val = list.get(k);
				int cnt = map.get(val);
				
				if (flag) {
					break;
				}
					
				for (int p = target; p >= val; p--) {
					if (dis[p-val]) {
						if (p == target) {
							sb.append(1).append("\n");
							flag = true;
							break;
						}
						for (int q = 1; q <= cnt; q++) {
							int check = (p-val) + (q*val);
							if (check > target) {
								break;
							}
							dis[check] = true; 
						}
					}
				}
				for (int w = 1; w <= cnt; w++) {
					dis[val*w] = true;
					if (val*w == target) {
						sb.append(1).append("\n");
						flag = true;
						break;
					}
					if (val*w > target) {
						break;
					}
				}
			}
			if (!flag) {
				if (dis[target]) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

}
