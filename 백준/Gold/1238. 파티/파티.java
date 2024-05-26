
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author kwang
 *
 */
public class Main {
	static class Dot implements Comparable<Dot>{
		int e;
		int time;
		public Dot (int e, int time){
			this.e = e;
			this.time = time;
		}
		public int compareTo(Dot o) {
			return Integer.compare(this.time, o.time);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Dot>[] fwd = new ArrayList[N+1]; // 파티로 가는 길
		List<Dot>[] back = new ArrayList[N+1]; // 파티에서 돌아오는 길
		for(int i = 0; i < N+1; i++) {
			fwd[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		}
		
		for(int i = 0;  i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			fwd[s].add(new Dot(e, time)); // 파티로 가는 경우(도착점을 기준으로 넣기) 
			back[e].add(new Dot(s, time)); // 파티에서 돌아오는 경우
		}
		
		int[] f = dijk(fwd, X, N); // 파티로 가는데 걸리는 시간
		int[] b = dijk(back, X, N); // 파티에서 돌아오는데 걸리는 시간
		
		int res = Integer.MIN_VALUE;
		for(int i = 1; i < N+1; i++) { // 가장 오래 걸린 시간 찾기
			int dis = f[i] + b[i];
			
			res = Math.max(res, dis);
		}
		
		System.out.println(res);
		
	}
	
	private static int[] dijk(List<Dot>[] list, int s, int N) {
		PriorityQueue<Dot> qu = new PriorityQueue<>();
		boolean[] visit = new boolean[N+1]; // 방문 체크
		int[] dp = new int[N+1]; // 최소 시간 저장할 배열
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		qu.add(new Dot(s, 0)); // 파티하는 곳 미리 넣어두기
		dp[s] = 0;
		
		while(!qu.isEmpty()) { // 우선순위 큐가 빌 때까지 반복
			Dot tmp = qu.poll();
			
			int end = tmp.e;
			
			if(visit[end]) { // 방문 체크
				continue;
			}
			
			visit[end] = true;
			
			for(Dot next : list[end]) {
				if(dp[end] + next.time < dp[next.e]) { // dp 배열에 저장된 시간 값이 합보다 클 경우
					dp[next.e] = dp[end] + next.time; // 합으로 바꿔주기
					qu.add(new Dot(next.e, dp[next.e])); // 다음 
				}
			}
		}
		
		return dp;
	}

}
