


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
		//StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			Set<String> set = new HashSet<>();
			List<String> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String tmp = br.readLine();
				set.add(tmp);
				list.add(tmp);
			}
			int flag = 0;
			for (String str: list) {
				String tmp = "";
				for (char c: str.toCharArray()) {
					tmp += c;
					//System.out.println(tmp);
					if (set.contains(tmp)) {
						if (tmp.length() == str.length()) {
							continue;
						}
						flag = 1;
						break;
					}
				}
				if (flag == 1) {
					break;
				}
			}
			if (flag == 1) {
				sb.append("NO").append("\n");
			} else {
				sb.append("YES").append("\n");
			}
		}
		System.out.println(sb);
	}

}
