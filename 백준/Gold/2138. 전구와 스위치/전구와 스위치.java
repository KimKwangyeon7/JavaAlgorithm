

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author kwang
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 개수
		String now = br.readLine(); // 전구 현재 상태
		String ans = br.readLine(); // 만들고자 하는 상태
		
		int[] a = new int[N]; // 첫번째 스위치를 안누른 경우
		for (int i = 0; i < N; i++) {
			a[i] = now.charAt(i)-'0';
		}
		int[] b = new int[N];
		for (int i = 0; i < N; i++) {
			b[i] = ans.charAt(i)-'0';
		}

		int[] c = Arrays.copyOf(a, N); // 첫번째 스위치를 누른 경우
		c[0] = 1 - c[0];
		c[1] = 1 - c[1];
		
		
		int cnt = 0;
        for (int i = 0; i < N-1; i++) {
            if (a[i]!=b[i]) {
                cnt++;
                a[i] = 1-a[i];
                a[i+1] = 1-a[i+1];
                if (i != N-2)
                    a[i+2] = 1-a[i+2];
            }
        }
        int res1 = a[N-1]!=b[N-1] ? -1 : cnt;
        
        cnt = 0;
        for (int i = 0; i < N-1; i++) {
            if (c[i]!=b[i]) {
                cnt++;
                c[i] = 1-c[i];
                c[i+1] = 1-c[i+1];
                if (i != N-2)
                    c[i+2] = 1-c[i+2];
            }
        }
        int res2 = c[N-1]!=b[N-1] ? -1 : cnt;
		
        if (res2 != -1) {
        	res2++;
        }
        
        if (res1 == -1) {
            System.out.println(res2);
        } else if (res2 == -1) {
            System.out.println(res1);
        } else {
            System.out.println(Math.min(res1, res2));
        }
	}

}
