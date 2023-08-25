import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김광연
 *
 */
public class Main {
	static class Node {
		int vertex;
		int dis;
		Node next;
		public Node(int vertex, int dis, Node next) {
			super();
			this.vertex = vertex;
			this.dis = dis;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		Node[] list = new Node[V];
		int[] minDis = new int[V];
		boolean[] visit = new boolean[V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			list[from-1] = new Node(to-1, dis, list[from-1]);
		}
		
		int INF = Integer.MAX_VALUE;
		Arrays.fill(minDis, INF);
		minDis[K-1] = 0; // 시작점
		int min = 0;
		int stopover = 0;
		
		for (int i = 0; i < V; i++) {
			min = INF;
			stopover = -1;
			for (int j = 0; j < V; j++) {
				if (!visit[j] && min > minDis[j]) {
					min = minDis[j];
					stopover = j;
				}
			}
			if (stopover == -1) {
				break;
			}
			visit[stopover] = true;
			for (Node tmp = list[stopover]; tmp != null; tmp = tmp.next) {
				if (!visit[tmp.vertex] && minDis[tmp.vertex] > min + tmp.dis) {
					minDis[tmp.vertex] = min + tmp.dis;
				}
			}		
		}
		for (int d: minDis) {
			if (d != INF) {
				sb.append(d).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}
		System.out.println(sb);
		
	}

}
