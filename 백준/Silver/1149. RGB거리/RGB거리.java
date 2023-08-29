import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1149_RGB거리
 * 
 * @author kwang
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] homes = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				homes[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N-1; i++) {
			homes[i+1][0] += Math.min(homes[i][1], homes[i][2]); // i+1번째 집에서 빨강을 고르면 i번째 집에서 초록 또는 파랑을 골랐을 때 중 최소값을 더함
			homes[i+1][1] += Math.min(homes[i][0], homes[i][2]); // i+1번째 집에서 초록을 고르면 i번째 집에서 빨강 또는 파랑을 골랐을 때 중 최소값을 더함
			homes[i+1][2] += Math.min(homes[i][1], homes[i][0]); // i+1번째 집에서 피랑을 고르면 i번째 집에서 초록 또는 빨강을 골랐을 때 중 최소값을 더함
		}
		System.out.println(Math.min(homes[N-1][0], Math.min(homes[N-1][1], homes[N-1][2])));
	}
}
