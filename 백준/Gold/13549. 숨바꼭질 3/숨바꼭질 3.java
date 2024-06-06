import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 * BJ_13549_숨바꼭질3
 * 메모리 시간
 * 아이디어
 * 
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		Queue<int[]> qu = new ArrayDeque<>();
		boolean[] visited = new boolean[100001]; // 방문 체크용 배열
		int ans = Integer.MAX_VALUE; // 최소값을 찾아야 하기 때문에 최대값으로 초기값 설정
        qu.offer(new int[] {N, 0}); // 시작점 값 넣기 (수빈이 위치)
        
        while(!qu.isEmpty()) {
            int[] tmp = qu.poll();
            visited[tmp[0]] = true; // 방문체크
             
            if (tmp[0] == K) { // 동생이 있는 곳이면
            	ans = Math.min(ans, tmp[1]); // 최소값과 비교 후 조정
            }
            
            if (tmp[0] * 2 <= 100000 && !visited[tmp[0]*2]) { // 순간이동할 때
            	qu.offer(new int[] {tmp[0]*2, tmp[1]});
            }
            
            if (tmp[0]+1 <= 100000 && !visited[tmp[0]+1]) { // 한 칸 앞으로 갈 때
            	qu.offer(new int[] {tmp[0]+1, tmp[1]+ 1});
            }
            
            if (tmp[0] - 1 >= 0 && !visited[tmp[0]-1]) { // 한 칸 뒤로 갈 때
            	qu.offer(new int[] {tmp[0]-1, tmp[1]+1});
            }
        }
        
        System.out.println(ans);
	}

}
