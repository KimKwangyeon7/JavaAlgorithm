
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
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int len = 2000000;
        visited = new boolean[len];
        visited[1] = true;
        for (int i = 2; i*i < len; i++) {
        	if (!visited[i]) {
        		for (int j = i*i; j < len; j+=i) {
        			visited[j] = true;
        		}
        	}
        }
        for (int i = N; i < len; i++) {
    		if (!visited[i] && isPelin(i)) {
    			System.out.println(i);
    			break;
    		}
        }
	}
	private static boolean isPelin(int num) {
		String str = num+"";
		int len = str.length();
		for (int i = 0; i < len/2; i++) {
			if (str.charAt(i) != str.charAt(len-1-i)) {
				return false;
			}
		}
		return true;
	}

}
