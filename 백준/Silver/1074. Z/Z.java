import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1074_Z
 * 
 * 아이디어
 * 라이브 수업에서 배운 분할 정복 사용
 * 메모리와 시간을 줄이기 위해 배열을 사용하지 않고 인덱스만 활용했고 시작하는 지점을 조정해 풀었다
 * 
 * @author 김광연
 *
 */
public class Main {

	static int r, c;
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int rs = 0;
		int cs = 0;
		int len = (int) Math.pow(2, N);
		// r과 c가 주어졌기 때문에 탐색하는 시간 줄이기 위해 더 가깝게 시작점 설정해주기
		if (r < len/2) { // 찾는 행과 열이 위치하는 사분면 찾기
			rs = 0;
			if (c < len/2) { // 1사분면
				cs = 0;
				cnt = 0;
			} else { // 2사분면
				cs = len/2;
				cnt = cs*cs; 
			}
		} else {
			rs = len/2;
			if (c < len/2) { // 4사분면
				cs = 0;
				cnt = (len/2) * (len/2) * 2;
			} else { // 3사분면
				cs = len/2;
				cnt = (len/2) * (len/2) * 3; 
			}
		}
		
		divArea(rs, cs, len);
	}

	static void divArea(int rs, int cs, int size) {
		if (size == 2) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (i+rs == r && j+cs == c) {
						System.out.println(cnt);
						System.exit(0);
					}
					cnt++;	
				}
			}
		} else {
			size = size/2;
			// Z 그리는 순서로 다시 재귀 호출
			divArea(rs, cs, size); // 1사분면
			divArea(rs, cs+size, size); // 2사분면
			divArea(rs+size, cs, size); // 4사분면
			divArea(rs+size, cs+size, size); // 3사분면
		}
	}

}
