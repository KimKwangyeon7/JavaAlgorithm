
import java.util.*;
import java.io.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int[] friend;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		friend = new int[n+1];
		List<Integer>[] list = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			friend[i] = i;
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			if (c == 'F') {
				union(n1, n2);
			} else {
				list[n1].add(n2);
				list[n2].add(n1);
			}
			
		}
		
		for (int i = 1; i <= n; i++) {
			List<Integer> tmp = list[i];
			if (!tmp.isEmpty()) {
				int p1 = i;
				for (int t: tmp) {
					List<Integer> enemies = list[t];
					if (!enemies.isEmpty()) {
						for (int e: enemies) {
							union(p1, e);
						}
					}
				}
			}
		}
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if (friend[i] == i) {
				answer++;
			}
		}
		System.out.println(answer);
	}	
	private static int find(int x) {
		if (x == friend[x]) {
			return x;
		}
		return friend[x] = find(friend[x]);
	}
	static void union(int x, int y) {
	    int rootX = find(x);
	    int rootY = find(y);

	    if (rootX != rootY) {
	        if (rootX < rootY) {
	            friend[rootY] = rootX;
	        } else {
	            friend[rootX] = rootY;
	        }
	    }
	}

}
