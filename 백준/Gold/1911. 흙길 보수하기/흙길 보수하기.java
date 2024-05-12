
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {

	static class Dot implements Comparable<Dot>{
	    int s;
	    int e;

	    public Dot(int s, int e) {
	        this.s = s;
	        this.e = e;
	    }
	    
	    @Override
	    public int compareTo(Dot o) {
	    	return Integer.compare(this.s, o.s);
	    }
	    
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물 웅덩이 개수
        int L = Integer.parseInt(st.nextToken()); // 널빤지 길이

        Dot[] dots = new Dot[N];

        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            dots[i] = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(dots);

        int cnt = 0;
        int s = 0;

        for (int i = 0; i < N; i++) {

            s = Math.max(s, dots[i].s); // 널빤지가 다음 웅덩이의 시작점을 넘었는지 넘지 않았는지 확인
            int k = 0; // i번째 구덩이를 덮기 위해서 필요한 널빤지의 길이
            while (k < dots[i].e - s) {
                k += L;
            }
            cnt = cnt + k / L; // t/L을 하면 필요한 널빤지의 길이가 나옴
            s += k; // start에서 널빤지의 길이만큼 +
        }
        System.out.println(cnt);
	}

}
