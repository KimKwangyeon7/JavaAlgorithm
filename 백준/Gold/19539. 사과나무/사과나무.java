import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_19539_사과나무_김광연
 * 
 * 아이디어
 * 구현 문제 -> 가능 여부 따지기
 * +1, +2 두가지 물뿌리개를 사용하고 한 곳에 둘다 사용도 되므로 +3도 가능
 * 우선 모든 나무의 높이의 합이 3의 배수여야 함 -> 매일 총 3의 높이를 증가시키기 때문
 * 최대로 키울 수 있는 높이가 3이기 때문에 모든 수를 3을로 나눔 -> 모든 높이들을 3으로 나눈 후 나머지가 1인 경우 그 1을 활용할 수 있게 해야함
 * 3으로 나눈 몫을 하나 줄여서 그 수의 나머지에 3을 더하면 
 * 즉 나눈 몫들과 나머지가 2인 경우의 합이 나머지가 1인 경우보다 같거나 커야함 
 * 
 * @author 김광연
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		int rem = 0; // 나머지
		int share = 0; // 몫;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			sum += tmp;
			rem += tmp % 2;
			share += tmp / 2;
		}
		if (sum % 3 == 0 && share >= rem) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
