
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] board = new int[200001];
		for (int i = 1; i < N+1; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		int[] dis = new int[100001];		
		int answer = 0;
		int start = 1;
		int end = 1;
		while (start <= end && end < N+1) {
			if (dis[board[end]] >= K) {
				answer = Math.max(answer, end-start);
				dis[board[start]]--;
				start++;
			} else {
				dis[board[end]]++;
				answer = Math.max(end-start+1, answer);
				end++;
			}
		}
		System.out.println(answer);
	}

}
