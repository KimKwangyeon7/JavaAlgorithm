
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @author kwang
 *
 */
public class Main {

	/**
	 * @param args
	 */
	static int N, M;
    static List<int[]>[] graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 신호 인덱스 정보도 함께 저장
            graph[a].add(new int[]{b, i});
            graph[b].add(new int[]{a, i});
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0L;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);

        while (!dq.isEmpty()) {
            int now = dq.poll();

            for (int[] edge : graph[now]) {
                int next = edge[0];
                int signalIdx = edge[1];

                // 현재 시간에서 언제 이동 가능한지 계산
                long nextTime = getNextTime(dist[now], signalIdx, M);

                if (nextTime < dist[next]) {
                    dist[next] = nextTime;
                    dq.add(next); // addFirst 사용도 가능 (빠른 우선순위)
                }
            }
        }

        System.out.println(dist[N]);
    }

    // 다음 가능한 신호등 통과 시간 계산
    private static long getNextTime(long currentTime, int idx, int M) {
        if (currentTime % M <= idx) {
            return currentTime + (idx - currentTime % M) + 1;
        } else {
            return currentTime + (M - (currentTime % M - idx)) + 1;
        }
    }
}
