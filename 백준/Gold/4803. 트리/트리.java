

/**
 * @author kwang
 *
 */
import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = 1;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            sb.append("Case ").append(t).append(": ");
            list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list[a].add(b);
                list[b].add(a);
            }

            visited = new boolean[n + 1];
            int treeCount = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    if (bfs(i)) {
                        treeCount++;
                    }
                }
            }

            if (treeCount == 0) {
                sb.append("No trees.");
            } else if (treeCount == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(treeCount).append(" trees.");
            }
            sb.append("\n");
            t++;
        }
        System.out.println(sb);
    }

    private static boolean bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1});
        visited[start] = true;
        boolean isTree = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], parent = cur[1];

            for (int next : list[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, node});
                } else if (next != parent) {
                    isTree = false;  // 부모가 아닌 방문 노드 발견 → 사이클 존재
                }
            }
        }
        return isTree;
    }
}

