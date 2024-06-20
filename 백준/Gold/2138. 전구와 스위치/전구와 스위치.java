

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
			a[i] = now.charAt(i)-'0'; // int 배열에 현재 상태 넣기
		}
		int[] b = new int[N]; // 정답 상태 
		for (int i = 0; i < N; i++) {
			b[i] = ans.charAt(i)-'0'; // int 배열에 정답 상태 넣기
		}

		int[] c = Arrays.copyOf(a, N); // 첫번째 스위치를 누른 경우
		c[0] = 1 - c[0]; // 첫번째 전구 변경
		c[1] = 1 - c[1]; // 두번째 전구 변경
		
		int res1 = 0;
		int res2 = 0;
		
		// 첫번째 스위치를 누르지 않은 경우
		int cnt = 0; // 누른 스위치 수
        for (int i = 0; i < N-1; i++) { 
            if (a[i]!=b[i]) { // 정답 전구 상태와 다르면
                cnt++; // 스위치 누르기
                a[i] = 1-a[i];
                a[i+1] = 1-a[i+1];
                if (i != N-2) // i가 N-2일 때는 가장 마지막 스위치를 누르는 경우 => i+2가 없음
                    a[i+2] = 1-a[i+2];
            }
        }
        if (a[N-1] != b[N-1]) {
        	res1 = -1;
        } else {
        	res1 = cnt;
        }
        
        // 첫번째 스위치를 누른 경우
        cnt = 1;
        for (int i = 0; i < N-1; i++) {
            if (c[i]!=b[i]) {
                cnt++;
                c[i] = 1-c[i];
                c[i+1] = 1-c[i+1];
                if (i != N-2)
                    c[i+2] = 1-c[i+2];
            }
        }
        if (c[N-1] != b[N-1]) {
        	res2 = -1;
        } else {
        	res2 = cnt;
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
