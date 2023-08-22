import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	
	static int L, C;
	static char[] board, res;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			board[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(board); // 우선 알파벳 순으로 정렬
		res = new char[L];
		bfs(0, 0);
		System.out.println(sb);
	}
	static void bfs(int size, int start) {
		if (size == L) {
			int cnt = 0; // 모음 개수
			for (char c: res) {
				if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') { // 모음일 경우
					cnt++;
				}
			}
			if (cnt >=1 && L-cnt >= 2) { // 모음이 1개 이상, 자음이 2개 이상일 때만 출력
				for (char c: res) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			res[size] = board[i];
			bfs(size+1, i+1);
		}
	}
}
