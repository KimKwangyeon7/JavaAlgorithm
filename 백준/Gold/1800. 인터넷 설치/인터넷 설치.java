import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, cost, freeUsed;

        public Node(int to, int cost, int freeUsed) {
            this.to = to;
            this.cost = cost;
            this.freeUsed = freeUsed;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, P, K;
    static List<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, cost});
            graph[b].add(new int[]{a, cost});
            maxCost = Math.max(maxCost, cost);
        }

        int left = 0, right = maxCost, answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean dijkstra(int maxAllowed) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[][] dist = new int[N + 1][K + 1]; // [노드][무료로 사용한 횟수]
        for (int i = 0; i <= N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        pq.offer(new Node(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.to == N) return true; // 도착 가능하면 true

            for (int[] next : graph[curr.to]) {
                int nextNode = next[0], nextCost = next[1];

                if (nextCost <= maxAllowed) {
                    if (dist[nextNode][curr.freeUsed] > curr.cost) {
                        dist[nextNode][curr.freeUsed] = curr.cost;
                        pq.offer(new Node(nextNode, curr.cost, curr.freeUsed));
                    }
                } else if (curr.freeUsed < K) {
                    if (dist[nextNode][curr.freeUsed + 1] > curr.cost + nextCost) {
                        dist[nextNode][curr.freeUsed + 1] = curr.cost + nextCost;
                        pq.offer(new Node(nextNode, curr.cost + nextCost, curr.freeUsed + 1));
                    }
                }
            }
        }

        return false;
    }
}
