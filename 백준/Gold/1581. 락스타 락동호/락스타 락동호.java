import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	/**
	 * 아이디어
	 * FS와 SF를 위주로 비교
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int FF = Integer.parseInt(st.nextToken());
		int FS = Integer.parseInt(st.nextToken());
		int SF = Integer.parseInt(st.nextToken());
		int SS = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		// FF로 시작
		if (FS == 0 && FF != 0) {
			System.out.println(FF);
		} else if (FS != 0 && FF == 0) {
			if (FS > SF) {
				cnt = SF+SF+1+SS;
				System.out.println(cnt);
			} else {
				cnt = FS+FS+SS;
				System.out.println(cnt);
			}
		} else if (FS != 0 && FF != 0) {
			if (FS > SF) {
				cnt = SF+SF+1+SS+FF;
				System.out.println(cnt);
			} else { 
				cnt = SS+FF+FS+FS;
				System.out.println(cnt);
			}
		} else { // FS와 FF 둘다 0일 때
			if (SF > 0) {
				cnt = SS + 1;
				System.out.println(cnt);
			} else {
				System.out.println(SS);
			}
		}
	}

}
