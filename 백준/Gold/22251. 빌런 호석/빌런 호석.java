

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	// 각 층마다 바꿀 때 드는 반전 횟수 저장한 배열
	static int[][] number = {{0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
        {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
        {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
        {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
        {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
        {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
        {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
        {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
        {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
        {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}};
	static int N, K, P, X, ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in))	;
        StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 엘레베이터 이용 층수
		K = Integer.parseInt(st.nextToken()); // 디스플레이 층수 자리수
		P = Integer.parseInt(st.nextToken()); // 최대 반전 개수
		X = Integer.parseInt(st.nextToken()); // 멈춘 층
		
		ans = 0;
		dfs(0, 0, 1, 0); // 인수는 자리수, 반전 개수, 10의 N승, 현재 층 
		System.out.println(ans-1);
	}
	
	static void dfs(int L, int cnt, int ten, int floor){
        if(cnt > P || floor > N) { // 최대 반전 개수와 비교 + 층수 비교
        	return;
        }
        if(L == K){
            if(floor!=0) {
            	ans++;
            }
            return;
        }

        for(int i = 0; i <= 9; i++){
            dfs(L+1, cnt + number[X/ten%10][i], ten*10, i*ten + floor);
        }
    }

}
