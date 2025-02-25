

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
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
        	list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
        	int x = Integer.parseInt(st.nextToken());
        	if (x != -1) {
        		list[x].add(i);
        	}
        }
        System.out.println(getTime(0));
	}

	private static int getTime(int idx) {
		List<Integer> times = new ArrayList<>();
		for(int tmp: list[idx]) {
			times.add(getTime(tmp));
		}
		if (times.isEmpty()) {
			return 0;
		}
		
		Collections.sort(times, Collections.reverseOrder());
		int max = 0;
		for (int i = 0; i < times.size(); i++) {
			max = Math.max(max, times.get(i) + i+1);
		}
		return max;
	}
}
