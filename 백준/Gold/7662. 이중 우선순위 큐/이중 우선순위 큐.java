

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
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                long num = Long.parseLong(st.nextToken());

                if (cmd == 'I') {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else if (cmd == 'D') {
                    if (map.isEmpty()) continue;

                    long target = (num == 1) ? map.lastKey() : map.firstKey();
                    if (map.get(target) == 1) {
                        map.remove(target);
                    } else {
                        map.put(target, map.get(target) - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.print(sb);
    }

}
